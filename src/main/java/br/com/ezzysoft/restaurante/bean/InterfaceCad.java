package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.util.JsfUtil;

import javax.ejb.Remote;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Remote
public interface InterfaceCad<E> {

    public void create();
    public void update();
    public void destroy();
    public void persist(JsfUtil.PersistAction persistAction, String successMessage);
}
