package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Unidade;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class UnidadeDAO implements CrudDAO<Unidade> {

    @Override
    public void salvar(Unidade unidade) throws ErroSistema {
        try {

        } catch (Exception e) {
            throw new ErroSistema("Erro ao tentar salvar!", e);
        }
    }

    
    public void deletar(Long id) throws ErroSistema {
        EntityManager em = getEM();
        Unidade unidade = em.find(Unidade.class, id);
        try {
            em.getTransaction().begin();
            em.remove(unidade); // delete
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new ErroSistema("Erro ao deletar a unidade", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Unidade> buscar() throws ErroSistema {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT u FROM Unidade u");
            return q.getResultList();
        } catch (Exception e) {
            throw new ErroSistema("Erro ao buscar as unidades!", e);
        } finally {
            em.close();
        }
    }

    @Override
    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Ezzysoft_RestaurantePU");
        return factory.createEntityManager();
    }

    @Override
    public Unidade findById(Long id) {
        EntityManager em = getEM();
        Unidade unidade = null;
        try {
            unidade = em.find(Unidade.class, id); //select
        } finally {
            em.close();
        }
        return unidade;
    }

    @Override
    public Unidade save(Unidade u) throws Exception {

        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (u.getId() == null) {
                em.persist(u); // insert    
            } else {
                if (!em.contains(u)) {
                    if (em.find(Unidade.class, u.getId()) == null) {
                        throw new Exception("Erro ao atualizar a unidade!");
                    }
                }
                u = em.merge(u); // update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return u;
    }

    @Override
    public void remove(Long id) {
        EntityManager em = getEM();
        Unidade unidade = em.find(Unidade.class, id);
        try {
            em.getTransaction().begin();
            em.remove(unidade); // delete
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Unidade> listAll() {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT u FROM Unidade u");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Unidade unidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
