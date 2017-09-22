package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ClienteDAO;
import br.com.ezzysoft.restaurante.entidade.Cliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBCliente")
@SessionScoped
public class MBCliente extends CrudBean<Cliente, ClienteDAO>{

    private ClienteDAO clienteDAO;
    
    @Override
    public ClienteDAO getDao() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }

    @Override
    public Cliente criarNovaEntidade() {
        return new Cliente();
    }
    
}
