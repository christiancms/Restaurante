/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.dao;

import br.com.restaurante.controle.DAO;
import br.com.restaurante.modelo.Unidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class UnidadeDAO implements DAO<Unidade> {

    @Override
    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("RestaurantePU");
        return factory.createEntityManager();
    }

    @Override
    public Unidade findById(Long id) {
        EntityManager em = getEM();
        Unidade unidade = null;
        try {
            unidade = em.find(Unidade.class, id); //select
        } finally {
            em.close();
        }
        return unidade;
    }

    @Override
    public Unidade save(Unidade u) throws Exception {

        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (u.getId() == null) {
                em.persist(u); // insert    
            } else {
                if (!em.contains(u)) {
                    if (em.find(Unidade.class, u.getId()) == null) {
                        throw new Exception("Erro ao atualizar a unidade!");
                    }
                }
                u = em.merge(u); // update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return u;
    }

    @Override
    public void remove(Long id) {
        EntityManager em = getEM();
        Unidade unidade = em.find(Unidade.class, id);
        try {
            em.getTransaction().begin();
            em.remove(unidade); // delete
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Unidade> listAll() {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT u FROM Unidade u");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}