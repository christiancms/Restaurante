package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Grupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author christian
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
    @Override
    public List<Grupo> findAll() {
        Query q = em.createNamedQuery("Grupo.findAllOrder");
        return (List<Grupo>)q.getResultList();
    }

    public List<Grupo> findCardapio(){
        return getEntityManager().createNamedQuery(Grupo.CARDAPIO).getResultList();
    }
}