package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Usuario;
import br.com.ezzysoft.restaurante.ws.UsuarioTransporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 03/11/17.
 */
@Stateless
@Path("/usuario")
public class UsuarioService extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public UsuarioService() {
        super(Usuario.class);
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuarios() {
        List<Usuario> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        UsuarioTransporter ut;
        String dados = "{\"usuarios\":[";
        if (!lista.isEmpty()) {
            for(Usuario elem:lista){
                ut = new UsuarioTransporter();
                ut.setId(elem.getId());
                ut.setUsuario(elem.getUsername());
                ut.setSenha(elem.getPassword());

        try {
            dados += mapper.writeValueAsString(ut);
            dados += ", ";
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    dados = dados.substring(0, dados.length() - 2);
}
        return dados + "]}";
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
