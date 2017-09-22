package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Cliente;
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
public class ClienteDAO implements CrudDAO<Cliente> {
    
    EntityManager em;

    @Override
    public void salvar(Cliente c) throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            if (c.getId() == null) {
                em.persist(c);
            } else {
                if (!em.contains(c)) {
                    if (em.find(Cliente.class, c.getId()) == null) {
                        throw new ErroSistema("Erro ao tentar salvar!");
                    }
                }
                c = em.merge(c);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Cliente entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT c FROM Cliente c");
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
    public Cliente findById(Long id) {
        em = getEM();
        Cliente cliente = null;
        try {
            cliente = em.find(Cliente.class, id); //select
        } finally {
            em.close();
        }
        return cliente;
    }

}
