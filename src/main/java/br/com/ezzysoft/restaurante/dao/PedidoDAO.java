package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Pedido;
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
public class PedidoDAO implements CrudDAO<Pedido>{
    
    EntityManager em;
    
    @Override
    public void salvar(Pedido p) throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            if (p.getId() == null) {
                em.persist(p); // insert    
            } else {
                if (!em.contains(p)) {
                    if (em.find(Pedido.class, p.getId()) == null) {
                        throw new ErroSistema("Erro ao tentar salvar!");
                    }
                }
                p = em.merge(p); // update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Pedido entidade) throws ErroSistema {
        try {

        } catch (Exception e) {
            throw new ErroSistema("Erro ao deletar o pedido", e);
        }
    }

    @Override
    public List<Pedido> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT p FROM Pedido p");
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
    public Pedido findById(Long id) {
        em = getEM();
        Pedido pedido = null;
        try {
            pedido = em.find(Pedido.class, id); //select
        } finally {
            em.close();
        }
        return pedido;
    }

    public void remove(Long id) {
        em = getEM();
        Pedido pedido = em.find(Pedido.class, id);
        try {
            em.getTransaction().begin();
            em.remove(pedido); // delete
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Pedido> listAll() {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT p FROM Pedido p");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
