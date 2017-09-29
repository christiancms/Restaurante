package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Empresa;
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
public class EmpresaDAO implements CrudDAO<Empresa> {

    EntityManager em;

    @Override
    public void salvar(Empresa e) throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            if (e.getId() == null) {
                em.persist(e);
            } else {
                if (!em.contains(e)) {
                    if (em.find(Empresa.class, e.getId()) == null) {
                        throw new ErroSistema("Erro ao atualizar a empresa!");
                    }
                }
                e = em.merge(e);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Empresa e) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empresa> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT e FROM Empresa e ");
            return q.getResultList();
        } catch (Exception ex) {
            throw new ErroSistema("Erro ao buscar as empresas!", ex);
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
    public Empresa findById(Long id) {
        em = getEM();
        Empresa empresa = null;
        try {
            empresa = em.find(Empresa.class, id);
        } finally {
            em.close();
        }
        return empresa;
    }

    @Override
    public List<Empresa> localizar(Empresa entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
