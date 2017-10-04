package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.facade.MarcaFacade;
import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.facade.UnidadeFacade;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.entidade.Marca;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import br.com.ezzysoft.restaurante.facade.GrupoFacade;
import br.com.ezzysoft.restaurante.facade.ProdutoFacade;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.model.SelectItem;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBProduto")
@SessionScoped
public class MBProduto implements Serializable {

    public List<SelectItem> getUnidades() throws ErroSistema {
        List<Unidade> unidades = getUnidadeFacade().findAll();
        List<SelectItem> itens = new ArrayList<>(unidades.size());
        for (Unidade u : unidades) {
            itens.add(new SelectItem(u.getId(), u.getSigla()));
        }
        return itens;
    }

    private UnidadeFacade getUnidadeFacade() {
        return facadeUnidade;
    }

    public List<SelectItem> getGrupos() throws ErroSistema {
        List<Grupo> grupos = getGrupoFacade().findAll();
        List<SelectItem> itens = new ArrayList<>(grupos.size());
        for (Grupo g : grupos) {
            itens.add(new SelectItem(g.getId(), g.getDescricao()));
        }
        return itens;
    }

    private GrupoFacade getGrupoFacade() {
        return facadeGrupo;
    }

    public List<SelectItem> getMarcas() throws ErroSistema {
        List<Marca> marcas = getMarcaFacade().findAll();
        List<SelectItem> itens = new ArrayList<>(marcas.size());
        for (Marca m : marcas) {
            itens.add(new SelectItem(m.getId(), m.getDescricao()));
        }
        return itens;
    }

    private MarcaFacade getMarcaFacade() {
        return facadeMarca;
    }

//    public Unidade getUnidade() {
//        return unidade;
//    }
//
//    public void setUnidade(Unidade u) {
//        this.unidade = u;
//    }
//
//    public Marca getMarca() {
//        return marca;
//    }
//
//    public void setMarca(Marca m) {
//        this.marca = m;
//    }
//
//    public Grupo getGrupo() {
//        return grupo;
//    }
//
//    public void setGrupo(Grupo g) {
//        this.grupo = g;
//    }
//
//    public void onLoad() {
//    }
////    
//    public String init(){
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        return sdf.format(new Date());
//    }
    @EJB
    private ProdutoFacade ejbFacade;
    @EJB
    private GrupoFacade facadeGrupo;
    @EJB
    private MarcaFacade facadeMarca;
    @EJB
    private UnidadeFacade facadeUnidade;
    private List<Produto> items = null;
    private Produto selected;
    private Grupo selectedGrupo;
    private Marca selectedMarca;
    private Unidade selectedUnidade;

    public MBProduto() {
    }

    public Produto getSelected() {
        if (selected != null) {
            if (selected.getGrupo().getId() != null) {
                selected.setGrupo(facadeGrupo.find(selected.getGrupo().getId()));
            }
            if (selected.getMarca().getId() != null) {
                selected.setMarca(facadeMarca.find(selected.getMarca().getId()));
            }
            if (selected.getUnidade().getId() != null) {
                selected.setUnidade(facadeUnidade.find(selected.getUnidade().getId()));
            }
        }
        return selected;
    }

    public void setSelected(Produto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProdutoFacade getFacade() {
        return ejbFacade;
    }

    public Produto prepareCreate() {
        selected = new Produto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("ProdutoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("ProdutoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("ProdutoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Produto> getItems() {
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

    public Produto getProduto(Long id) {
        return getFacade().find(id);
    }

    public List<Produto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Produto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Grupo getSelectedGrupo() {
        return selectedGrupo;
    }

    public void setSelectedGrupo(Grupo selectedGrupo) {
        this.selectedGrupo = selectedGrupo;
    }

    public Marca getSelectedMarca() {
        return selectedMarca;
    }

    public void setSelectedMarca(Marca selectedMarca) {
        this.selectedMarca = selectedMarca;
    }

    public Unidade getSelectedUnidade() {
        return selectedUnidade;
    }

    public void setSelectedUnidade(Unidade selectedUnidade) {
        this.selectedUnidade = selectedUnidade;
    }

    @FacesConverter(forClass = Produto.class)
    public static class MBProdutoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBProduto controller = (MBProduto) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "MBProduto");
            return controller.getProduto(getKey(value));
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
            if (object instanceof Produto) {
                Produto o = (Produto) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                        "object {0} is of type {1}; expected type: {2}",
                        new Object[]{object, object.getClass().getName(), Produto.class.getName()});
                return null;
            }
        }

    }
}
