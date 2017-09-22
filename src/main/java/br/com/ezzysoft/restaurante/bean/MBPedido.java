package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.PedidoDAO;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author christian
 */
@ManagedBean(name = "MBPedido")
@SessionScoped
public class MBPedido extends CrudBean<Pedido, PedidoDAO>{
    
    private PedidoDAO pedidoDAO;

    @Override
    public PedidoDAO getDao() {
        if (pedidoDAO == null) {
            pedidoDAO = new PedidoDAO();
        }
        return pedidoDAO;
    }

    @Override
    public Pedido criarNovaEntidade() {
        return new Pedido();
    }
    
    
    
}
