package br.com.ezzysoft.restaurante.config;

import br.com.ezzysoft.restaurante.bean.MBLogin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by christian on 15/10/17.
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession(true);
        String url = httpRequest.getRequestURL().toString();
        System.out.println("Caminho atual: " + url);
        System.out.println("Atributos sessão: " + httpRequest.getSession().getAttribute("username"));

        if (!url.contains("/ws") && !url.contains("/images") &&
                !url.contains("/cardapio") && !url.contains("faces.resource/")) {
            if (session.getAttribute("username") == null && precisaAutenticar(url)) {
                System.out.println("Caminho de contexto: "+httpRequest.getContextPath());
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsf");
            } else {
                chain.doFilter(req, resp);
            }
        }else {
            chain.doFilter(req, resp);
        }
//        String sessionLogin = (String) httpRequest.getSession().getAttribute("username");
//
//        if (sessionLogin == null) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsf");
//        } else {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsf");
//        }
//        chain.doFilter(req, resp);// So, just continue request.
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private boolean precisaAutenticar(String url) {
        return !url.contains("login.jsf");
    }

}
