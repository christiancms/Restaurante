package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.Mesa;
import br.com.ezzysoft.restaurante.facade.MesaFacade;
import br.com.ezzysoft.restaurante.facade.UsuarioFacade;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbMesa")
@SessionScoped
public class MBMesa implements Serializable {

    @ManagedProperty(value = "#{mbUsuario}")
    private MBUsuario mbUsuario = new MBUsuario();

    @EJB
    private MesaFacade ejbFacade;
    @EJB
    private UsuarioFacade facadeUsuario;
    private List<Mesa> items = null;
    private Mesa selected;
    private Integer quantidade;
    private Integer nrInicial;

    public MBMesa() {
    }

    public Mesa getSelected() {
        return selected;
    }

    public void setSelected(Mesa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MesaFacade getFacade() {
        return ejbFacade;
    }

    public MBUsuario getMbUsuario() {
        return mbUsuario;
    }

    public void setMbUsuario(MBUsuario mbUsuario) {
        this.mbUsuario = mbUsuario;
    }

    public Mesa prepareCreate() {
        selected = new Mesa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (!selected.getDescricao().isEmpty()) {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("MesaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            List<Mesa> mesas = new ArrayList<>();
            Mesa m;
            int j = getQuantidade();
            for (int i = getNrInicial(); j > 0; j--) {
                m = new Mesa();
                m.setDescricao("Mesa "+i);
                i++;
                mesas.add(m);
            }
            for (Mesa elem:mesas) {
                getFacade().edit(elem);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("MesaCreated"));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("MesaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("MesaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Mesa> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getNrInicial() {
        return nrInicial;
    }

    public void setNrInicial(Integer nrInicial) {
        this.nrInicial = nrInicial;
    }

    public Mesa getMesa(Integer id) {
        return getFacade().find(id);
    }

    public List<Mesa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Mesa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Mesa.class)
    public static class MesaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBMesa controller = (MBMesa) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mbMesa");
            return controller.getMesa(getKey(value));
        }

        Integer getKey(String value) {
            Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Mesa) {
                Mesa o = (Mesa) object;
                return getStringKey(o.getId().intValue());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mesa.class.getName()});
                return null;
            }
        }

    }
    @PostConstruct
    public void init() {
        String userid = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        mbUsuario.setSelected(facadeUsuario.find(Long.parseLong(userid)));
    }
}
