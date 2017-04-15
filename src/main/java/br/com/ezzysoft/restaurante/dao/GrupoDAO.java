package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Grupo;
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
public class GrupoDAO implements CrudDAO<Grupo>{
    
    @Override
    public void salvar(Grupo grupo) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deletar(Long id) throws ErroSistema {
        try {

        } catch (Exception e) {
            throw new ErroSistema("Erro ao deletar o grupo", e);
        }
    }
    

    @Override
    public List<Grupo> buscar() throws ErroSistema {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT g FROM Grupo g");
            return q.getResultList();
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
    public Grupo findById(Long id) {
        EntityManager em = getEM();
        Grupo grupo = null;
        try {
            grupo = em.find(Grupo.class, id); //select
        } finally {
            em.close();
        }
        return grupo;
    }

    @Override
    public Grupo save(Grupo g) throws ErroSistema {
        
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (g.getId() == null) {
                em.persist(g); // insert    
            } else {
                if (!em.contains(g)) {
                    if (em.find(Grupo.class, g.getId()) == null) {
                        throw new ErroSistema("Erro ao atualizar o grupo!");
                    }
                }
                g = em.merge(g); // update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return g;
    }

    @Override
    public void remove(Long id) {
        EntityManager em = getEM();
        Grupo grupo = em.find(Grupo.class, id);
        try {
            em.getTransaction().begin();
            em.remove(grupo); // delete
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Grupo> listAll() {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT g FROM Grupo g");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    
    @Override
    public void deletar(Grupo grupo) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
