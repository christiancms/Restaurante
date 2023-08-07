package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ColaboradorDAO;
import br.com.ezzysoft.restaurante.entidade.Colaborador;
import br.com.ezzysoft.restaurante.facade.ColaboradorFacade;
import br.com.ezzysoft.restaurante.facade.UsuarioFacade;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static org.primefaces.component.focus.Focus.PropertyKeys.context;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbColaborador")
@SessionScoped
public class MBColaborador implements ColaboradorFacade {

    @ManagedProperty(value = "#{mbUsuario}")
    private MBUsuario mbUsuario = new MBUsuario();

    @EJB
    private ColaboradorFacade ejbFacade;
    @EJB
    private UsuarioFacade facadeUsuario;
    private List<Colaborador> items = null;
    private Colaborador selected;

    public MBColaborador() {
    }

    public MBUsuario getMbUsuario() {
        return mbUsuario;
    }

    public void setMbUsuario(MBUsuario mbUsuario) {
        this.mbUsuario = mbUsuario;
    }

    public Colaborador getSelected() {
        return selected;
    }

    public void setSelected(Colaborador selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ColaboradorFacade getFacade() {
        return ejbFacade;
    }

    public Colaborador prepareCreate() {
        selected = new Colaborador();
        initializeEmbeddableKey();
        return selected;
    }

    public UsuarioFacade getFacadeUsuario() {
        return facadeUsuario;
    }

    public void setFacadeUsuario(UsuarioFacade facadeUsuario) {
        this.facadeUsuario = facadeUsuario;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("ColaboradorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("ColaboradorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("ColaboradorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public List<Colaborador> getItems() {
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
                    JsfUtil.addErrorMessage(ex,
                            ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex,
                        ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Colaborador getColaborador(Long id) {
        return getFacade().find(id);
    }

    public List<Colaborador> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Colaborador> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Colaborador.class)
    public static class ColaboradorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBColaborador controller = (MBColaborador) facesContext.getApplication().getELResolver()
                    .getValue(facesContext.getELContext(), null, "mbColaborador");
            return controller.getColaborador(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Colaborador) {
                Colaborador o = (Colaborador) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                        "object {0} is of type {1}; expected type: {2}",
                        new Object[] { object, object.getClass().getName(), Colaborador.class.getName() });
                return null;
            }
        }

    }

    @PostConstruct
    public void init() {
        String userid = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        mbUsuario.setSelected(facadeUsuario.find(Long.parseLong(userid)));
    }

    private MBColaborador mbColaborador;

    public MBColaboradorAdapter(MBColaborador mbColaborador) {
        this.mbColaborador = mbColaborador;
    }

    @Override
    public Colaborador find(Long id) {
        return mbColaborador.getColaborador(id);
    }

    @Override
    public List<Colaborador> findAll() {
        return mbColaborador.getItems();
    }

    @Override
    public void edit(Colaborador colaborador) {
        mbColaborador.update();
    }

    @Override
    public void remove(Colaborador colaborador) {
        mbColaborador.destroy();
    }

}
