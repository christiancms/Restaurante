package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Status;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.ws.StatusTransporter;
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
@Path("/status")
public class StatusService extends AbstractFacade<Status> {


    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public StatusService() {
        super(Status.class);
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStatus() {
        List<Status> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        StatusTransporter st;
        String dados = "{\"status\":[";
        if (!lista.isEmpty()) {
            for (Status elem : lista) {
                st = new StatusTransporter();
                st.setId(elem.getId());
                st.setIndice(elem.getIndice());
                st.setClasse(elem.getClasse());
                st.setOpcao(elem.getOpcao());

                try {
                    dados += mapper.writeValueAsString(st);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(StatusService.class.getName()).log(Level.SEVERE, null, ex);
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
