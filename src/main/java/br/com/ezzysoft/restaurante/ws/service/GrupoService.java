package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.facade.GrupoFacade;
import br.com.ezzysoft.restaurante.ws.GrupoTransporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
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
@Path("/grupo")
public class GrupoService extends AbstractFacade<Grupo> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public GrupoService() {
        super(Grupo.class);
    }

    @EJB
    GrupoFacade facade;

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGrupos() {
        List<Grupo> lista = facade.findCardapio();
        ObjectMapper mapper = new ObjectMapper();
        GrupoTransporter gt;
        String dados = "{\"grupos\":[";
        if (!lista.isEmpty()) {
            for (Grupo elem:lista) {
                gt = new GrupoTransporter();
                gt.setId(elem.getId());
                gt.setDescricao(elem.getDescricao());

                try {
                    dados += mapper.writeValueAsString(gt);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(GrupoService.class.getName()).log(Level.SEVERE, null, ex);
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
