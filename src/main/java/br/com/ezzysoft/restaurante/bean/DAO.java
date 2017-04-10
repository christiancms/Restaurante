package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 * @param <E>
 */
public interface DAO<E> {

    public EntityManager getEM() throws ErroSistema;

    public E findById(Long id) throws ErroSistema;

    public E save(E e) throws ErroSistema;

    public void remove(Long id) throws ErroSistema;

    public void delete(E entidade) throws ErroSistema;

    public List<E> listAll() throws ErroSistema;
}
