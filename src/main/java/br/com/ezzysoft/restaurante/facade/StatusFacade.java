package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Status;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by christian on 31/10/17.
 */
@Stateless
public class StatusFacade extends AbstractFacade<Status>{

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusFacade() {
        super(Status.class);
    }

    public Status findEnumPedido(Long id) {
        return getEntityManager().find(Status.class, id);
    }
    public Status findEnumProduto(Long id) {
        return getEntityManager().find(Status.class, id);
    }

    public List<Status> getEnumPedido(){
        return getEntityManager().createNamedQuery(Status.ENUMPEDIDO).getResultList();
    }

    public Status getIndiceEnumPedido(Integer indice){
        List<Status> estados = getEntityManager().createNamedQuery(Status.INDICEENUMPEDIDO).setParameter("statusIndice", indice).getResultList();
        return estados.get(0);
    }

    public List<Status> getEnumProduto(){
        Query q = em.createNamedQuery(Status.ENUMPRODUTO);
        return q.getResultList();
    }

    public Status getIndiceEnumProduto(Integer indice){
        List<Status> estados = getEntityManager().createNamedQuery(Status.INDICEENUMPRODUTO).setParameter("statusIndice", indice).getResultList();
        return estados.get(0);
    }
}
