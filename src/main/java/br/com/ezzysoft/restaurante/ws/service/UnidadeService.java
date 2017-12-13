package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Unidade;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.ws.UnidadeTransporter;
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
 *
 * @author christian
 */
@Stateless
@Path("/unidade")
public class UnidadeService extends AbstractFacade<Unidade> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public UnidadeService() {
        super(Unidade.class);
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUnidades() {
        List<Unidade> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        UnidadeTransporter ut;
        String dados = "{\"unidades\":[";
        if (!lista.isEmpty()) {
            for (Unidade elem: lista){
                ut = new UnidadeTransporter();
                ut.setId(elem.getId());
                ut.setDescricao(elem.getDescricao());
                ut.setSigla(elem.getSigla());

                try {
                    dados += mapper.writeValueAsString(ut);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(UnidadeService.class.getName()).log(Level.SEVERE, null, ex);
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
