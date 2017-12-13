package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Mesa;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;

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
public class MesaFacade extends AbstractFacade<Mesa> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MesaFacade() {
        super(Mesa.class);
    }

    @Override
    public List<Mesa> findAll() {
        Query q = em.createNamedQuery("Mesa.findAll");
        return (List<Mesa>)q.getResultList();
    }

    public List<Mesa> buscarGrafico() {
        return getEntityManager().createNamedQuery(Mesa.MESASOCUPADAS).getResultList();
    }
}
