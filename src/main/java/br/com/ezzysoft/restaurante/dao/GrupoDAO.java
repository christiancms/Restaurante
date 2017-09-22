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
public class GrupoDAO implements CrudDAO<Grupo> {

    EntityManager em;
    
    @Override
    public void salvar(Grupo g) throws ErroSistema {
        em = getEM();
        if (g.getFoto() == null) {

        }

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
    }

    public byte[] loadImage(Integer id) {
        em = getEM();
        return em.find(Grupo.class, id).getFoto();
    }

    public void deletar(Long id) throws ErroSistema {
        try {

        } catch (Exception e) {
            throw new ErroSistema("Erro ao deletar o grupo", e);
        }
    }

    @Override
    public List<Grupo> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT g FROM Grupo g ORDER BY g.descricao ");
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
    public Grupo findById(Long id) {
        em = getEM();
        Grupo grupo = null;
        try {
            grupo = em.find(Grupo.class, id); //select
        } finally {
            em.close();
        }
        return grupo;
    }

    
    public Grupo buscaById() {
        Long id = 1l;
        em = getEM();
        Grupo grupo = null;
        try {
            grupo = em.find(Grupo.class, id); //select
        } finally {
            em.close();
        }
        return grupo;
    }

    
    public byte[] findImageById(Long id) {
        em = getEM();
        Grupo grupo = null;
        try {
            grupo = em.find(Grupo.class, id); //select
        } finally {
        }
        return grupo.getFoto();
    }

    @Override
    public void deletar(Grupo grupo) throws ErroSistema {
    }

}
