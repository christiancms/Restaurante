package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.bean.*;
import br.com.ezzysoft.restaurante.entidade.Marca;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.ArrayList;
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

    @Override
    public void salvar(Marca entidade) throws ErroSistema {
        try {

        } catch (Exception e) {
            throw new ErroSistema("Erro ao tentar salvar!", e);
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
        try {
            List<Marca> marcas = new ArrayList<>();
            return marcas;
        } catch (Exception e) {
            throw new ErroSistema("Erro ao buscar as marcas!", e);
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Ezzysoft_RestaurantePU");
        return factory.createEntityManager();
    }
    
    @Override
    public Marca findById(Long id) {
        EntityManager em = getEM();
        Marca marca = null;
        try {
            marca = em.find(Marca.class, id); //select
        } finally {
            em.close();
        }
        return marca;
    }

    @Override
    public Marca save(Marca m) throws Exception {
        
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (m.getId() == null) {
                em.persist(m); // insert    
            } else {
                if (!em.contains(m)) {
                    if (em.find(Marca.class, m.getId()) == null) {
                        throw new Exception("Erro ao atualizar a unidade!");
                    }
                }
                m = em.merge(m); // update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return m;
    }

    @Override
    public void remove(Long id) {
        EntityManager em = getEM();
        Marca marca = em.find(Marca.class, id);
        try {
            em.getTransaction().begin();
            em.remove(marca); // delete
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Marca> listAll() {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT m FROM Marca m");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
