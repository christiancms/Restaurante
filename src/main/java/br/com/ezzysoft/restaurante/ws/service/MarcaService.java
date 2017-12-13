package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Marca;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.ws.MarcaTransporter;
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
@Path("/marca")
public class MarcaService extends AbstractFacade<Marca> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public MarcaService() {
        super(Marca.class);
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMarcas() {
        List<Marca> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        MarcaTransporter mt;
        String dados = "{\"marcas\":[";
        if (!lista.isEmpty()) {
            for (Marca elem: lista) {
                mt = new MarcaTransporter();
                mt.setId(elem.getId());
                mt.setDescricao(elem.getDescricao());

                try {
                    dados += mapper.writeValueAsString(mt);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(MarcaService.class.getName()).log(Level.SEVERE, null, ex);
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
