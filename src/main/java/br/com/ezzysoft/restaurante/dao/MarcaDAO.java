package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Marca;
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
public class MarcaDAO implements CrudDAO<Marca>{
    
    EntityManager em;
    
    @Override
    public void salvar(Marca m) throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            if (m.getId() == null) {
                em.persist(m); // insert    
            } else {
                if (!em.contains(m)) {
                    if (em.find(Marca.class, m.getId()) == null) {
                        throw new ErroSistema("Erro ao tentar salvar!");
                    }
                }
                m = em.merge(m); // update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Marca entidade) throws ErroSistema {
        try {

        } catch (Exception e) {
            throw new ErroSistema("Erro ao deletar a marca", e);
        }
    }

    @Override
    public List<Marca> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT m FROM Marca m ORDER BY m.descricao");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EzzysoftPU");
        return factory.createEntityManager();
    }

    @Override
    public Marca findById(Long id) {
        em = getEM();
        Marca marca = null;
        try {
            marca = em.find(Marca.class, id); //select
        } finally {
            em.close();
        }
        return marca;
    }

    public void remove(Long id) {
        em = getEM();
        Marca marca = em.find(Marca.class, id);
        try {
            em.getTransaction().begin();
            em.remove(marca); // delete
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Marca> listAll() {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT m FROM Marca m ORDER BY m.descricao");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Marca> localizar(Marca entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
