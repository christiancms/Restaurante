package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Produto;
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
public class ProdutoDAO implements CrudDAO<Produto> {

    EntityManager em;
    
    @Override
    public Produto findById(Long id) {
        em = getEM();
        Produto produto = null;
        try {
            produto = em.find(Produto.class, id); //select
        } finally {
            em.close();
        }
        return produto;
    }

    @Override
    public void salvar(Produto p) throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            if (p.getId() == null) {
                em.persist(p); // insert    
            } else {
                if (!em.contains(p)) {
                    if (em.find(Produto.class, p.getId()) == null) {
                        throw new ErroSistema("Erro ao atualizar a unidade!");
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
    public void deletar(Produto entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> buscar() throws ErroSistema {
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT p FROM Produto p");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Produto> buscarGrafico() throws ErroSistema{
        em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT p FROM Produto p "
                    + "INNER JOIN Marca m"
                    + " ON p.marca.id=m.id");
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
    public List<Produto> localizar(Produto entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
