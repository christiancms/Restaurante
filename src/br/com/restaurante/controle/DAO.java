/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.controle;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 * @param <T>
 */
public interface DAO<T> {
    public EntityManager getEM();
    public T findById(Long id);
    public T save(T t)throws Exception;
    public void remove(Long id);
    public List<T> listAll();
}
