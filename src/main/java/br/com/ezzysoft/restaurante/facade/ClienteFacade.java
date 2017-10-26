package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author christian
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public List<Cliente> findAll() {
        Query q = em.createNamedQuery("Cliente.findAllOrder");
        return (List<Cliente>)q.getResultList();
    }
    
}
