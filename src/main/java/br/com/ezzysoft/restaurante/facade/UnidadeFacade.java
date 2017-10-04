package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.dao.CrudDAO;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Stateless
public class UnidadeFacade extends AbstractFacade<Unidade> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UnidadeFacade() {
        super(Unidade.class);
    }
    
//    @Override
//    public void salvar(Unidade u) throws ErroSistema {
//        em = getEM();
//        try {
//            em.getTransaction().begin();
//            if (u.getId() == null) {
//                em.persist(u); // insert    
//            } else {
//                if (!em.contains(u)) {
//                    if (em.find(Unidade.class, u.getId()) == null) {
//                        throw new ErroSistema("Erro ao atualizar a unidade!");
//                    }
//                }
//                u = em.merge(u); // update
//            }
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }

    
//    public void deletar(Long id) throws ErroSistema {
//        em = getEM();
//        Unidade unidade = em.find(Unidade.class, id);
//        try {
//            em.getTransaction().begin();
//            em.remove(unidade); // delete
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            throw new ErroSistema("Erro ao deletar a unidade", e);
//        } finally {
//            em.close();
//        }
//    }
    
//    @Override
//    public List<Unidade> buscar() throws ErroSistema {
//        em = getEM();
//        try {
//            em.getTransaction().begin();
//            Query q = em.createQuery("SELECT u FROM Unidade u ORDER BY u.descricao");
//            return q.getResultList();
//        } catch (Exception e) {
//            throw new ErroSistema("Erro ao buscar as unidades!", e);
//        } finally {
//            em.close();
//        }
//    }

//    @Override
//    public EntityManager getEM() {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EzzysoftPU");
//        return factory.createEntityManager();
//    }

//    @Override
//    public Unidade findById(Long id) {
//        em = getEM();
//        Unidade unidade = null;
//        try {
//            unidade = em.find(Unidade.class, id); //select
//        } finally {
//            em.close();
//        }
//        return unidade;
//    }

//    public void remove(Long id) {
//        em = getEM();
//        Unidade unidade = em.find(Unidade.class, id);
//        try {
//            em.getTransaction().begin();
//            em.remove(unidade); // delete
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }

//    public List<Unidade> listAll() {
//        em = getEM();
//        try {
//            em.getTransaction().begin();
//            Query q = em.createQuery("SELECT u FROM Unidade u ORDER BY u.descricao");
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }

//    @Override
    public void deletar(Unidade unidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Unidade> localizar(Unidade entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
