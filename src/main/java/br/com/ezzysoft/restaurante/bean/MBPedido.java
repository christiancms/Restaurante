package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ColaboradorDAO;
import br.com.ezzysoft.restaurante.entidade.*;
import br.com.ezzysoft.restaurante.facade.*;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import com.lowagie.text.*;
import org.primefaces.mobile.component.footer.Footer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

/**
 * @author christian
 */
@ManagedBean(name = "mbPedido")
@SessionScoped
public class MBPedido implements InterfaceCad<Pedido>, Serializable {


    @EJB
    private PedidoFacade facade;
    @EJB
    private ColaboradorFacade facadeColaborador;
    @EJB
    private StatusFacade facadeStatus;
    @EJB
    private ProdutoFacade facadeProduto;
    @EJB
    private ItemPedidoFacade facadeIpedido;

    @ManagedProperty(value = "#{mbClienteSelect}")
    private MBClienteSelect mbClienteSelect = new MBClienteSelect();

    @ManagedProperty(value = "#{mbGarcomSelect}")
    private MBGarcomSelect mbGarcomSelect = new MBGarcomSelect();

    private Pedido selected;
    private ItemPedido selectedItem;
    private List<Pedido> items = null;
    private List<ItemPedido> itensPedido = null;
    private Colaborador selectedColaborador;
    private Double totalPedido = 0d;
    private Status selectedStatus;
    private List<Pedido> pedidosFiltrados;
    private Date dtInicial;
    private Date dtFinal;
    private String statusPed;
    private String garcomPedido;

    private String totalAprovado;
    private String totalCancelado;
    private String totalEntregue;
    private String totalFaturado;
    private String totalPendente;
    private String totalPronto;

    public MBPedido() {
    }

    public Pedido getSelected() {
        if (selected != null) {
//            selected.setColaborador(facadeColaborador.find(selected.getColaborador().getId()));

            if (selected.getStatus().getId() != null) {
                selected.setStatus(facadeStatus.findEnumPedido(selected.getStatus().getId()));
            }
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
        return facade;
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
        if (!refresh) {  //onLoad
            if (items == null) {
                items = new ArrayList<>();
                items = getFacade().getPedidoAtual(new Date());
//                items = calculaItensPedido(items);
            }
        } else {  // onUpdate
            items = new ArrayList<>();
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Pedido> porStatus(String descricao){
        return facade.porStatus(descricao);
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

    public Status getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Status selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    @FacesConverter(forClass = Pedido.class)
    public static class MBPedidoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBPedido controller = (MBPedido) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mbPedido");
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


    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
//        if (items != null) {
//            items = getFacade().getPedidoAtual(new Date());
//        }
        if (!FacesContext.getCurrentInstance().isPostback()) {
            System.out.println("Postback: " + shf.format(new Date()));
        }
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public List<SelectItem> getStatus() throws ErroSistema {
        List<Status> status = getStatusFacade().getEnumPedido();
        List<SelectItem> itens = new ArrayList<>(status.size());
        for (Status s : status) {
            itens.add(new SelectItem(s.getId(), s.getOpcao()));
        }
        return itens;
    }

    public StatusFacade getStatusFacade() {
        return facadeStatus;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.A4.rotate());
//        pdf.setMargins(2, 2, 2, 2);

        ServletContext servletContext = (ServletContext)
                FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resources" +
                File.separator + "images" +
                File.separator + "logo-web.png";
        Image logotipo = Image.getInstance(logo);
        logotipo.scalePercent(25);

        HeaderFooter header = new HeaderFooter(new Phrase("Tabela de vendas"), false);

        pdf.setHeader(header);

        if (!pdf.isOpen()) {
            pdf.open();
            pdf.add(logotipo);
        }
    }

    public List<String> getGarcons() {
        List<Colaborador> listagem = facadeColaborador.findAll();
        List<String> temp = new ArrayList<>();
        for (Colaborador elem : listagem) {
            temp.add(elem.getNome());
        }
        return temp;
    }

    public List<String> getStatusPedido() {
        List<Status> listagem = facadeStatus.getEnumPedido();
        List<String> temp = new ArrayList<>();
        for (Status elem : listagem) {
            temp.add(elem.getOpcao());
        }
        return temp;
    }

    public String getTotal() {
        Double total = 0d;

        for (Pedido ped : getItems(true)) {
            total += ped.getTotalPedido();
        }
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;

        return df.format(total);
    }

    public String getTotalAprovado() {
        Double aprovado = 0d;

        for (Pedido ped : getItems(true)) {
            if (ped.getStatus().getOpcao().equals("APROVADO")) {
                aprovado += ped.getTotalPedido();
            }
        }

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(aprovado);
    }

    public void setTotalAprovado(String totalAprovado) {
        this.totalAprovado = totalAprovado;
    }

    public String getTotalCancelado() {
        Double cancelado = 0d;

        for (Pedido ped : getItems(true)) {
            if (ped.getStatus().getOpcao().equals("CANCELADO")) {
                cancelado += ped.getTotalPedido();
            }
        }

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(cancelado);
    }

    public void setTotalCancelado(String totalCancelado) {
        this.totalCancelado = totalCancelado;
    }

    public String getTotalEntregue() {
        Double entregue = 0d;

        for (Pedido ped : getItems(true)) {
            if (ped.getStatus().getOpcao().equals("ENTREGUE")) {
                entregue += ped.getTotalPedido();
            }
        }

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(entregue);
    }

    public void setTotalEntregue(String totalEntregue) {
        this.totalEntregue = totalEntregue;
    }

    public String getTotalFaturado() {
        Double faturado = 0d;

        for (Pedido ped : getItems(true)) {
            if (ped.getStatus().getOpcao().equals("FATURADO")) {
                faturado += ped.getTotalPedido();
            }
        }

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(faturado);
    }

    public void setTotalFaturado(String totalFaturado) {
        this.totalFaturado = totalFaturado;
    }

    public String getTotalPendente() {
        Double pendente = 0d;

        for (Pedido ped : getItems(true)) {
            if (ped.getStatus().getOpcao().equals("PENDENTE")) {
                pendente += ped.getTotalPedido();
            }
        }

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(pendente);
    }

    public void setTotalPendente(String totalPendente) {
        this.totalPendente = totalPendente;
    }

    public String getTotalPronto() {
        Double pronto = 0d;

        for (Pedido ped : getItems(true)) {
            if (ped.getStatus().getOpcao().equals("PRONTO")) {
                pronto += ped.getTotalPedido();
            }
        }

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        DecimalFormat df = (DecimalFormat) nf;
        return df.format(pronto);
    }

    public void setTotalPronto(String totalPronto) {
        this.totalPronto = totalPronto;
    }

    public Date getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

    public void setStatusPed(String statusPed) {
        this.statusPed = statusPed;
    }

    public String getStatusPed() {
        return statusPed;
    }

    public String getGarcomPedido() {
        return garcomPedido;
    }

    public void setGarcomPedido(String garcomPedido) {
        this.garcomPedido = garcomPedido;
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public void pesquisar() {
    }

    public MBClienteSelect getMbClienteSelect() {
        return mbClienteSelect;
    }

    public void setMbClienteSelect(MBClienteSelect mbClienteSelect) {
        this.mbClienteSelect = mbClienteSelect;
    }

    public MBGarcomSelect getMbGarcomSelect() {
        return mbGarcomSelect;
    }

    public void setMbGarcomSelect(MBGarcomSelect mbGarcomSelect) {
        this.mbGarcomSelect = mbGarcomSelect;
    }
}