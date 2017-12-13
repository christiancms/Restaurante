package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Colaborador;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.ws.ColaboradorTransporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author christian
 */
@Stateless
@Path("/colaborador")
public class ColaboradorService extends AbstractFacade<Colaborador> {

@PersistenceContext(unitName = "EzzysoftPU")    
private EntityManager em;

    public ColaboradorService() {
        super(Colaborador.class);
    }
    
    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getColaboradores() {
        List<Colaborador> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        ColaboradorTransporter ct;
        String dados = "{\"colaboradores\":[";
        if (!lista.isEmpty()) {
            for (Colaborador elem : lista) {
                ct = new ColaboradorTransporter();
                ct.setIdColaborador(elem.getId());
                ct.setNome(elem.getNome());
                ct.setUsuarioId(elem.getId());

                try {
                    dados += mapper.writeValueAsString(ct);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ColaboradorService.class.getName()).log(Level.SEVERE, null, ex);
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

