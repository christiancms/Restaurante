package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Cliente;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.ws.ClienteTransporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author christian
 */
@Stateless
@Path("/cliente")
public class ClienteService extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public ClienteService() {
        super(Cliente.class);
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClientes() {
        List<Cliente> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        ClienteTransporter ct;
        String dados = "{\"clientes\":[";
        if (!lista.isEmpty()) {
            for (Cliente elem : lista) {
                ct = new ClienteTransporter();
                ct.setId(elem.getId());
                ct.setNome(elem.getNome());

                try {
                    dados += mapper.writeValueAsString(ct);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
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
