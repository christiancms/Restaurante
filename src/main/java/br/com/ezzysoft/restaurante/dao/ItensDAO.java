package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author christian
 */
public class ItensDAO implements CrudDAO<ItemPedido>{

    @Override
    public void salvar(ItemPedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(ItemPedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> buscar() throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> localizar(ItemPedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityManager getEM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPedido findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
