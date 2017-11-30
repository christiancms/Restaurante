package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.*;
import br.com.ezzysoft.restaurante.facade.*;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbProdutoComposto")
@SessionScoped
public class MBProdutoComposto implements Serializable {

    @ManagedProperty(value = "#{mbUsuario}")
    private MBUsuario mbUsuario = new MBUsuario();
//    @ManagedProperty(value = "#{mbProdutoSelect}")
//    private MBProdutoSelect mbProdutoSelect = new MBProdutoSelect();

    @EJB
    private ProdutoFacade ejbFacade;
    @EJB
    private GrupoFacade facadeGrupo;
    @EJB
    private MarcaFacade facadeMarca;
    @EJB
    private UnidadeFacade facadeUnidade;
    @EJB
    private StatusFacade facadeStatus;
    @EJB
    private UsuarioFacade facadeUsuario;

    private List<Produto> items = null;
    private List<Produto> selectedItems;
    private Produto selected;
    private ItemProduto selectedIProd;
    private Grupo selectedGrupo;
    private Marca selectedMarca;
    private Unidade selectedUnidade;
    private Status selectedStatus;

    public MBProdutoComposto() {
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
            if (selected.getStatus().getId() != null) {
                selected.setStatus(facadeStatus.findEnumProduto(selected.getStatus().getId()));
            }
        }
        return selected;
    }

    public ItemProduto getSelectedIProd() {
        return selectedIProd;
    }

    public void setSelectedIProd(ItemProduto selectedIProd) {
        this.selectedIProd = selectedIProd;
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

    public MBUsuario getMbUsuario() {
        return mbUsuario;
    }

    public void setMbUsuario(MBUsuario mbUsuario) {
        this.mbUsuario = mbUsuario;
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
            items = getFacade().getProdutosComposto();
        }
        return items;
    }

    public List<Produto> getItensCompoeProduto() {
        return getFacade().getItensCompoeCardapio();
    }

    public List<Produto> getItensProduto(Long produtoId) {
        return getFacade().getItensProduto(produtoId);
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
//                    getFacade().edit(selected);
                    getFacade().salvar(selected);
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

    public Status getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Status selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public List<Produto> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<Produto> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @FacesConverter(forClass = Produto.class)
    public static class MBProdutoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBProdutoComposto controller = (MBProdutoComposto) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mbProduto");
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

    public List<SelectItem> getStatus() throws ErroSistema {
        List<Status> status = getStatusFacade().getEnumProduto();
        List<SelectItem> itens = new ArrayList<>(status.size());
        for (Status s : status) {
            itens.add(new SelectItem(s.getId(), s.getOpcao()));
        }
        return itens;
    }

    public StatusFacade getStatusFacade() {
        return facadeStatus;
    }

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Produto> atualiza() {
        items = getFacade().findAll();
        return items;
    }

    public void produtoSelecionado(SelectEvent event) {
        Produto produto = (Produto) event.getObject();
        ItemProduto itemLista = new ItemProduto();
        itemLista.setProduto(produto);
    }

    @PostConstruct
    public void init(){
//        this.selected = new Produto();
//        getItems();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        return sdf.format(new Date());
        String userid = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        mbUsuario.setSelected(facadeUsuario.find(Long.parseLong(userid)));
    }
}
