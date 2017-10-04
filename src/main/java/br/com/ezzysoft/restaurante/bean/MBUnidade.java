package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.UnidadeDAO;
import br.com.ezzysoft.restaurante.facade.UnidadeFacade;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBUnidade")
@SessionScoped
public class MBUnidade implements Serializable {

    @EJB
    private UnidadeFacade ejbFacade;
    private List<Unidade> items = null;
    private Unidade selected;
    
//    private UnidadeDAO unidadeDAO;

    //@Override
//    public UnidadeDAO getDao() {
//        if (unidadeDAO == null) {
//            unidadeDAO = new UnidadeDAO();
//        }
//        return unidadeDAO;
//    }

    //@Override
//    public Unidade criarNovaEntidade() {
//        return new Unidade();
//    }
//
//    public UnidadeDAO getUnidadeDAO() {
//        return unidadeDAO;
//    }
//
//    public void setUnidadeDAO(UnidadeDAO unidadeDAO) {
//        this.unidadeDAO = unidadeDAO;
//    }

    public MBUnidade() {
    }

    public Unidade getSelected() {
        return selected;
    }

    public void setSelected(Unidade selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UnidadeFacade getFacade() {
        return ejbFacade;
    }

    public Unidade prepareCreate() {
        selected = new Unidade();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("UnidadeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("UnidadeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("UnidadeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Unidade> getItems() {
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

    public Unidade getUnidade(Long id) {
        return getFacade().find(id);
    }

    public List<Unidade> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Unidade> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Unidade.class)
    public static class MBUnidadeConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBUnidade controller = (MBUnidade) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "MBUnidade");
            return controller.getUnidade(getKey(value));
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
            if (object instanceof Unidade) {
                Unidade o = (Unidade) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Unidade.class.getName()});
                return null;
            }
        }

    }

    
    }
