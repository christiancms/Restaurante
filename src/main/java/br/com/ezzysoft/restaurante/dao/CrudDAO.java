package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 * @param <E> Classe Entidade que extende a interface
 */
public interface CrudDAO<E> {
    
    public void salvar(E entidade) throws ErroSistema;
    public void deletar(E entidade) throws ErroSistema;
    public List<E> buscar() throws ErroSistema;
    
    public EntityManager getEM();
    public E findById(Long id);
    public E save(E e)throws Exception;
    public void remove(Long id);
    public List<E> listAll()throws ErroSistema;
}
