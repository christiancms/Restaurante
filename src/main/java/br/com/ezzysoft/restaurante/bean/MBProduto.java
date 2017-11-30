package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.*;
import br.com.ezzysoft.restaurante.facade.*;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.JsfUtil.PersistAction;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import br.com.ezzysoft.restaurante.util.export.PDFOptions;
import com.lowagie.text.*;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.context.RequestContext;

import java.io.File;
import java.io.IOException;
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
import javax.servlet.ServletContext;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbProduto")
@SessionScoped
public class MBProduto implements Serializable {

    @ManagedProperty(value = "#{mbUsuario}")
    private MBUsuario mbUsuario = new MBUsuario();

    ////
//    public String init(){
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        return sdf.format(new Date());
//    }
    @EJB
    private ProdutoFacade facadeProduto;
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
    private Produto selected;
    private Grupo selectedGrupo;
    private Marca selectedMarca;
    private Unidade selectedUnidade;
    private Status selectedStatus;
    private ExcelOptions excelOpt;
    private PDFOptions pdfOpt;

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
            if (selected.getStatus().getId() != null) {
                selected.setStatus(facadeStatus.findEnumProduto(selected.getStatus().getId()));
            }
        }
        return selected;
    }

    public void setSelected(Produto selected) {
        this.selected = selected;
    }

    // Atribui chave incorporável
    protected void setEmbeddableKeys() {
    }

    // Inicializa chave incorporável
    protected void initializeEmbeddableKey() {
    }

    private ProdutoFacade getFacade() {
        return facadeProduto;
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
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Produto> getMobi() {
        if (items == null) {
            items = getFacade().getCardapio();
        }
        return items;
    }

    public Produto onBlur() {
        return selected = facadeProduto.find(selected.getId());
    }

    public Produto calcMargem(Produto produto) {
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("saved", true);    //basic parameter
//        context.addCallbackParam("user", selected);     //pojo as json
//
//        String myid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("txtPrecoCompra");
//        System.out.println("myid : " + myid);
//
//        myid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("txtMargemLuc");
//        System.out.println("myid 2: " + myid);

        if (produto.getPrecoCompra() > 0d && produto.getPercLucro() > 0d) {
            double vlrVenda = produto.getPrecoCompra() * (1 + (produto.getPercLucro() / 100));
            produto.setPrecoVenda(vlrVenda);
        }
        return produto;
    }

    public List<Produto> getItensGrupo(long grupoId) {
        return getFacade().produtoPorGrupo(grupoId);
    }

    private void persist(PersistAction persistAction, String successMessage) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
//                    getFacade().edit(selected);
                    if (persistAction.equals(PersistAction.CREATE)) {
                        selected.setDataCadastro(sdf.parse(sdf.format(new Date())));
                    }
                    selected = calcMargem(selected);
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

    @FacesConverter(forClass = Produto.class)
    public static class MBProdutoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBProduto controller = (MBProduto) facesContext.getApplication().getELResolver().
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

    public List<SelectItem> getGruposc() throws ErroSistema {
        List<Grupo> grupos = getGrupoFacade().findCardapio();
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

        HeaderFooter header = new HeaderFooter(new Phrase("Tabela de produtos"), false);
        pdf.setHeader(header);
        if (!pdf.isOpen()) {
            pdf.open();
            pdf.add(logotipo);
        }
    }

    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }

    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

    public void customizationOptions() {
//        excelOpt = new ExcelOptions();
//        excelOpt.setFacetBgColor("#F88017");
//        excelOpt.setFacetFontSize("10");
//        excelOpt.setFacetFontColor("#0000ff");
//        excelOpt.setFacetFontStyle("BOLD");
//        excelOpt.setCellFontColor("#00ff00");
//        excelOpt.setCellFontSize("8");

        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#F88017");
        pdfOpt.setFacetFontColor("#0000ff");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("12");
        float[] columnWidths = new float[]{0.1f,0.3f,0.1f,0.1f,0.1f,0.1f,0.1f};
        pdfOpt.setColumnWidths(columnWidths);
    }

    @PostConstruct
    public void init() {
//        this.selected = new Produto();
//        getItems();
        String userid = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        mbUsuario.setSelected(facadeUsuario.find(Long.parseLong(userid)));
    }
}
