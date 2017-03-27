/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.dao;

import br.com.restaurante.controle.DAO;
import br.com.restaurante.modelo.Grupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class GrupoDAO implements DAO<Grupo>{

    @Override
    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("RestaurantePU");
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
    public Grupo save(Grupo g) throws Exception {
        
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (g.getId() == null) {
                em.persist(g); // insert    
            } else {
                if (!em.contains(g)) {
                    if (em.find(Grupo.class, g.getId()) == null) {
                        throw new Exception("Erro ao atualizar a unidade!");
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
    
}
