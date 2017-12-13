package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.facade.AbstractFacade;

import java.util.List;
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
@Path("/itenspedido")
public class ItenspedidoService extends AbstractFacade<ItemPedido> {
 
    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public ItenspedidoService() {
        super(ItemPedido.class);
    }

    @POST
    @Path("/cria")
    @Override
    @Consumes(MediaType.APPLICATION_XML)
    public void create(ItemPedido entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, ItemPedido entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ItemPedido find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @POST
    @Path("/lista")
    @Override
    @Produces(MediaType.APPLICATION_XML)
    public List<ItemPedido> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ItemPedido> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
