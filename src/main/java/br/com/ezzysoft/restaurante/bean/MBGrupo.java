package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.facade.GrupoFacade;
import br.com.ezzysoft.restaurante.facade.UsuarioFacade;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;
import br.com.ezzysoft.restaurante.util.Util;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbGrupo")
@SessionScoped
//@ApplicationScoped
public class MBGrupo implements Serializable {

    @ManagedProperty(value = "#{mbUsuario}")
    private MBUsuario mbUsuario = new MBUsuario();

    @EJB
    private GrupoFacade ejbFacade;
    @EJB
    private UsuarioFacade facadeUsuario;
    private List<Grupo> items = null;
    private Grupo selected;

    public MBGrupo() {
    }

    public Grupo getSelected() {
        return selected;
    }

    public void setSelected(Grupo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GrupoFacade getFacade() {
        return ejbFacade;
    }

    public MBUsuario getMbUsuario() {
        return mbUsuario;
    }

    public void setMbUsuario(MBUsuario mbUsuario) {
        this.mbUsuario = mbUsuario;
    }
    public Grupo prepareCreate() {
        selected = new Grupo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("GrupoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("GrupoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("GrupoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Grupo> getItems() {
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

    public Grupo getGrupo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Grupo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Grupo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Grupo.class)
    public static class MBGrupoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBGrupo controller = (MBGrupo) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mbGrupo");
            return controller.getGrupo(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Grupo) {
                Grupo o = (Grupo) object;
                return getStringKey(o.getId().intValue());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Grupo.class.getName()});
                return null;
            }
        }

    }

//    private GrupoDAO grupoDAO;
//    private byte[] imagem;
//    private String nomeFoto;
//    private UploadedFile arquivo;
//    Util util;
//
//    public UploadedFile getArquivo() {
//        return arquivo;
//    }
//
//    public void setArquivo(UploadedFile arquivo) {
//        this.arquivo = arquivo;
//    }
//
////    @Override
//    public GrupoDAO getDao() {
//        if (grupoDAO == null) {
//            grupoDAO = new GrupoDAO();
//        }
//        return grupoDAO;
//    }
//
////    @Override
//    public Grupo criarNovaEntidade() {
//        return new Grupo();
//    }
//
////    public void doUpload(FileUploadEvent event) throws ErroSistema {
////        setArquivo(event.getFile());
////        setNomeFoto(arquivo.getFileName());
////        setImagem(arquivo.getContents());
////        this.getEntidade().setFoto(getImagem());
////    }
//
//    public StreamedContent getPicture() throws ErroSistema, IOException {
//        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
//        if (id != null && grupoDAO.buscar() != null && !grupoDAO.buscar().isEmpty()) {
//            Long imagemId = Long.parseLong(id);
//            for (Grupo g : grupoDAO.buscar()) {
//                if (Objects.equals(g.getId(), imagemId)) {
//                    return new DefaultStreamedContent(new ByteArrayInputStream(g.getFoto()));
//                }
//            }
//        }
//        return new DefaultStreamedContent();
//    }
//
//    public String getNomeFoto() {
//        return nomeFoto;
//    }
//
//    public void setNomeFoto(String nomeFoto) {
//        this.nomeFoto = nomeFoto;
//    }
//
//    public byte[] getImagem() {
//        return imagem;
//    }
//
//    public void setImagem(byte[] imagem) {
//        this.imagem = imagem;
//    }
//
//    public GrupoDAO getGrupoDAO() {
//        return grupoDAO;
//    }
//
//    public void setGrupoDAO(GrupoDAO grupoDAO) {
//        this.grupoDAO = grupoDAO;
//    }
//

    @PostConstruct
    public void init() {
        String userid = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        mbUsuario.setSelected(facadeUsuario.find(Long.parseLong(userid)));
    }
}
