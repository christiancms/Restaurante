package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;
import br.com.ezzysoft.restaurante.ws.ProdutoTransporter;
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
@Path("/produto")
public class ProdutoService extends AbstractFacade<Produto> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public ProdutoService() {
        super(Produto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Produto entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Produto entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Produto find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @POST
    @Override
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public List<Produto> findAll() {
        return super.findAll();
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProdutos() {
        List<Produto> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        ProdutoTransporter pt;
        String dados = "{\"produtos\":[";
        if (!lista.isEmpty()) {
            for (Produto elem : lista) {
                pt = new ProdutoTransporter();
                pt.setIdProduto(elem.getId());
                pt.setDescricao(elem.getDescricao());
                pt.setDataCadastro(elem.getDataCadastro());
                pt.setPreco(elem.getPrecoVenda());
                pt.setGrupoId(elem.getGrupo().getId().longValue());
                pt.setMarcaId(elem.getMarca().getId().longValue());
                pt.setUnidadeId(elem.getUnidade().getId());
                pt.setNomeGrupo(elem.getGrupo().getDescricao());

                try {
                    dados += mapper.writeValueAsString(pt);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ProdutoService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dados = dados.substring(0, dados.length() - 2);
        }
        return dados + "]}";
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Produto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
