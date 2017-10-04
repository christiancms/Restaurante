package br.com.ezzysoft.restaurante.bean;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void salvar(T entity) {
        getEntityManager().persist(entity);    
    }

    public void deletar(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T findById(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> buscar() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

   
    
}
