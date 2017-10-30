package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.entidade.Pedido;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 *
 * @author christian
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }

    public List<Pedido> getPedidoAtual(Date dataAtual){
     return getEntityManager().createNamedQuery(Pedido.LISTPEDIDOSATUAL).setParameter("dataAtual", dataAtual).getResultList();
    }

    public List<ItemPedido> getItensPedido(Long pedidoId){
        return getEntityManager().createNamedQuery(ItemPedido.ITENSPORPEDIDO).setParameter("pedidoId", pedidoId).getResultList();
    }
    
}
