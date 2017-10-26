package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.util.exception.NegocioException;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Classe Abstrata não pode ser instanciada.
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 * A classe que extender esta Fachada Abstrata deve passar como
 * @param <T> a classe entidade que será gerenciada.
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    // Método será sobreposto com anotação '@Override'
    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        try {
            getEntityManager().merge(entity);
        } catch (OptimisticLockException e){
            throw new NegocioException("Erro de concorrência. Esse "+entity.toString()+" já foi alterado anteriormente.");
        }

    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
