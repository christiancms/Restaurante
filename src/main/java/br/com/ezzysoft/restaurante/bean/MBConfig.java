package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.*;
import br.com.ezzysoft.restaurante.facade.*;
import br.com.ezzysoft.restaurante.util.JsfUtil;
import br.com.ezzysoft.restaurante.util.Util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbConfig")
@SessionScoped
public class MBConfig implements Serializable {

    @ManagedProperty(value = "#{mbUsuario}")
    private MBUsuario mbUsuario = new MBUsuario();

    @EJB
    private ConfiguracaoFacade facade;
    @EJB
    private ClienteFacade facadeCliente;
    @EJB
    private UsuarioFacade facadeUsuario;
    @EJB
    private ColaboradorFacade facadeColaborador;
    @EJB
    private GrupoFacade facadeGrupo;
    @EJB
    private MarcaFacade facadeMarca;
    @EJB
    private UnidadeFacade facadeUnidade;
    @EJB
    private ProdutoFacade facadeProduto;
    @EJB
    private StatusFacade facadeStatus;

    private String username;
    private String password;
    private Configuracao selected;
    private List<Configuracao> items = null;

    Util util = new Util();

    public MBConfig() {
    }

    public void criarRegistros() {
        if (util.loginMaster(getUsername(), getPassword())) {

            Cliente cliente = facadeCliente.find(1l);
            if (cliente == null) {
                cliente = new Cliente("Cliente Padrão");
                facadeCliente.create(cliente);
            }
            Usuario usuario = facadeUsuario.find(1l);
            if (usuario == null) {
                usuario = new Usuario("ezzyadm", "teste123");
                facadeUsuario.create(usuario);
            }
            Colaborador colaborador = facadeColaborador.find(1l);
            if (colaborador == null) {
                colaborador = new Colaborador("ezzyadm", "teste123","Colaborador 01");
                facadeColaborador.create(colaborador);
            }
            Grupo grupo = facadeGrupo.find(1l);
            if (grupo == null) {
                grupo = new Grupo("Grupo 01");
                facadeGrupo.create(grupo);
            }
            Marca marca = facadeMarca.find(1l);
            if (marca == null) {
                marca = new Marca("Marca 01");
                facadeMarca.create(marca);
            }
            Unidade unidade = facadeUnidade.find(1l);
            if (unidade == null) {
                unidade = new Unidade("Unidade", "un");
                facadeUnidade.create(unidade);
            }

            Status st01 = new Status("Produto",0,"ATIVO",1);
            facadeStatus.create(st01);
            Status st02 = new Status("Produto",1,"INATIVO",1);
            facadeStatus.create(st02);
            Status st03 = new Status("Produto",2,"EMFALTA",1);
            facadeStatus.create(st03);
            Status st04 = new Status("Produto",3,"DEFASADO",1);
            facadeStatus.create(st04);
            Status st05 = new Status("Pedido",0,"APROVADO",1);
            facadeStatus.create(st05);
            Status st06 = new Status("Pedido",1,"CANCELADO",1);
            facadeStatus.create(st06);
            Status st07 = new Status("Pedido",2,"ENTREGUE",1);
            facadeStatus.create(st07);
            Status st08 = new Status("Pedido",3,"FATURADO",1);
            facadeStatus.create(st08);
            Status st09 = new Status("Pedido",4,"PENDENTE",1);
            facadeStatus.create(st09);
            Status st10 = new Status("Pedido",5,"PRONTO",1);
            facadeStatus.create(st10);

            Status status = facadeStatus.getIndiceEnumProduto(0);
            Produto produto = facadeProduto.find(1l);
            if (produto == null) {
                produto = new Produto(
                        "Produto padrão", "Produto do menu Config", 10d, 15d, new Date(),
                        "1234567890", 50.0d, status, false, grupo, marca, unidade);
                facadeProduto.create(produto);
            }
            JsfUtil.addSuccessMessage("Registros criados com sucesso");
        }
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("ConfiguracaoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("ConfiguracaoUpdated"));
    }

    public Configuracao prepareCreate() {
        selected = new Configuracao();
        initializeEmbeddableKey();
        return selected;
    }

    public List<Configuracao> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
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

    public ConfiguracaoFacade getFacade() {
        return facade;
    }

    // Atribui chave incorporável
    protected void setEmbeddableKeys() {
    }

    // Inicializa chave incorporável
    protected void initializeEmbeddableKey() {
    }

    public Configuracao getSelected() {
        return selected;
    }

    public void setSelected(Configuracao selected) {
        this.selected = selected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Configuracao getConfiguracao(Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Configuracao.class)
    public static class ConfiguracaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MBConfig controller = (MBConfig) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mbConfig");
            return controller.getConfiguracao(getKey(value));
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
            if (object instanceof Configuracao) {
                Configuracao o = (Configuracao) object;
                return getStringKey(o.getId().intValue());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Configuracao.class.getName()});
                return null;
            }
        }

    }

    public String getBrowserName() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");

        if(userAgent.contains("MSIE")){
            return "Internet Explorer";
        }
        if(userAgent.contains("Firefox")){
            return "Firefox";
        }
        if(userAgent.contains("Chrome")){
            return "Chrome";
        }
        if(userAgent.contains("Opera")){
            return "Opera";
        }
        if(userAgent.contains("Safari")){
            return "Safari";
        }
        return "Unknown";
    }

    public MBUsuario getMbUsuario() {
        return mbUsuario;
    }

    public void setMbUsuario(MBUsuario mbUsuario) {
        this.mbUsuario = mbUsuario;
    }

    @PostConstruct
    public void init() {
        List<Configuracao> lista = getItems();
        if (lista.isEmpty()) {
            prepareCreate();
        } else {
            setSelected(lista.get(0));
        }
        String userid = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        if (userid != null) {
            mbUsuario.setSelected(facadeUsuario.find(Long.parseLong(userid)));
        }
    }

}
