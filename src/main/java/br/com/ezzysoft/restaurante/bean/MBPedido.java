package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ColaboradorDAO;
import br.com.ezzysoft.restaurante.entidade.Colaborador;
import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import br.com.ezzysoft.restaurante.facade.ColaboradorFacade;
import br.com.ezzysoft.restaurante.facade.PedidoFacade;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 * @author christian
 */
@ManagedBean(name = "MBPedido")
@SessionScoped
public class MBPedido implements InterfaceCad<Pedido>, Serializable {


    @EJB
    private PedidoFacade ejbFacade;
    @EJB
    private ColaboradorFacade facadeColaborador;
    private Pedido selected;
    private ItemPedido selectedItem;
    private List<Pedido> items = null;
    private List<ItemPedido> itensPedido = null;
    private Colaborador selectedColaborador;
    private Double totalPedido = 0d;

    public MBPedido() {
    }

    public Pedido getSelected() {
        if (selected != null) {
            selected.setColaborador(facadeColaborador.find(selected.getColaborador().getId()));
        }
        return selected;
    }

    public void setSelected(Pedido selected) {
        this.selected = selected;
    }


    // Atribui chave incorporável
    protected void setEmbeddableKeys() {
    }

    // Inicializa chave incorporável
    protected void initializeEmbeddableKey() {
    }

    private PedidoFacade getFacade() {
        return ejbFacade;
    }

    public Pedido prepareCreate() {
        selected = new Pedido();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("PedidoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("PedidoUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("PedidoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
                if (selected.getVersao() != null) {
                    JsfUtil.addSuccessMessage(" Versão: " + selected.getVersao());
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ColaboradorFacade getFacadeColaborador() {
        return facadeColaborador;
    }

    public void setFacadeColaborador(ColaboradorFacade facadeColaborador) {
        this.facadeColaborador = facadeColaborador;
    }

    public List<Pedido> getItems(boolean refresh) {
        if (!refresh) {
            if (items == null) {
                items = getFacade().findAll();
            }
        } else {
            items = null;
            items = getFacade().findAll();
        }
        return calculaItensPedido(items);
    }

    public List<ItemPedido> getItensPedido(Long pedidoId) {
        return getFacade().getItensPedido(pedidoId);
    }

    public List<Pedido> calculaItensPedido(List<Pedido> listaPedidos) {
        Double accum = 0d;
        int i = 0;
        if (!listaPedidos.isEmpty()) {
            for (Pedido ped : listaPedidos) {
                itensPedido = getFacade().getItensPedido(ped.getId());
                for (ItemPedido ipedido : itensPedido) {
                    accum += ipedido.getProduto().getPrecoVenda() * ipedido.getQuantidade();
                }
                listaPedidos.get(i).setTotalPedido(accum);
                accum = 0d;
                i++;
            }
        }
        return listaPedidos;
    }

    public List<SelectItem> getColaboradores() throws ErroSistema {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        List<Colaborador> colaboradores = colaboradorDAO.buscar();
        List<SelectItem> itens = new ArrayList<>(colaboradores.size());
        for (Colaborador c : colaboradores) {
            itens.add(new SelectItem(c.getId(), c.getNome()));
        }
        return itens;
    }

    public Colaborador getSelectedColaborador() {
        return selectedColaborador;
    }

    public void setSelectedColaborador(Colaborador selectedColaborador) {
        this.selectedColaborador = selectedColaborador;
    }

    public Pedido getPedido(Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Pedido.class)
    public static class MBPedidoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBPedido controller = (MBPedido) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "MBPedido");
            return controller.getPedido(getKey(value));
        }

        Long getKey(String value) {
            Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pedido) {
                Pedido o = (Pedido) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                        "object {0} is of type {1}; expected type: {2}",
                        new Object[]{object, object.getClass().getName(), Pedido.class.getName()});
                return null;
            }
        }
    }

    @PostConstruct
    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
        items = getFacade().getPedidoAtual(new Date());
        if (!FacesContext.getCurrentInstance().isPostback()) {
            System.out.println("Postback: "+shf.format(new Date()));
        }
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }
}
