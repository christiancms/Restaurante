package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Colaborador;
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
public class ColaboradorDAO implements CrudDAO<Colaborador> {

    EntityManager em;

    @Override
    public void salvar(Colaborador c) throws ErroSistema {
        em = getEM();
        try{
            em.getTransaction().begin();
            if (c.getId() == null) {
                em.persist(c);
            } else {
                if (!em.contains(c)) {
                    if(em.find(Colaborador.class, c.getId()) == null) {
                        throw new ErroSistema("Erro ao atualizar o colaborador!");
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
    public void deletar(Colaborador c) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Colaborador> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT c FROM Colaborador c");
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
    public Colaborador findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Colaborador> localizar(Colaborador entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
