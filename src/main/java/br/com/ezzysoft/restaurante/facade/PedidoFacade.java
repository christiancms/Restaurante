package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Pedido;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author christian
 */
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
    
}
