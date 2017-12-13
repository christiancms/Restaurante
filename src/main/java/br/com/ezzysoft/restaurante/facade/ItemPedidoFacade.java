package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.entidade.Pedido;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by christian on 12/12/17.
 */
@Stateless
public class ItemPedidoFacade extends AbstractFacade<ItemPedido> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemPedidoFacade() {
        super(ItemPedido.class);
    }
}
