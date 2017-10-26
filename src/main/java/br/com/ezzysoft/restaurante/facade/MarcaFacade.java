package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Marca;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Stateless
public class MarcaFacade extends AbstractFacade<Marca> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }

    @Override
    public List<Marca> findAll() {
        Query q = em.createNamedQuery("Marca.findAllOrder");
        return (List<Marca>)q.getResultList();
    }
}
