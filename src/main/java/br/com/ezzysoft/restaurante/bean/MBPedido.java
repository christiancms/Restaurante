package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ColaboradorDAO;
import br.com.ezzysoft.restaurante.dao.PedidoDAO;
import br.com.ezzysoft.restaurante.entidade.Colaborador;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author christian
 */
@ManagedBean(name = "MBPedido")
@SessionScoped
public class MBPedido implements Serializable {
    
    private PedidoDAO pedidoDAO;
    private Colaborador colaborador = new Colaborador();

//    @Override
    public PedidoDAO getDao() {
        if (pedidoDAO == null) {
            pedidoDAO = new PedidoDAO();
        }
        return pedidoDAO;
    }

//    @Override
    public Pedido criarNovaEntidade() {
        return new Pedido();
    }

    public List<Pedido> localizarItens(Pedido pedido)throws ErroSistema{
        if (pedido != null) {
        return pedidoDAO.localizarItens(pedido);    
        }
        return new ArrayList<>();
    }
    
    public List<SelectItem> getColaboradores() throws ErroSistema {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        List<Colaborador> colaboradores = colaboradorDAO.buscar();
        List<SelectItem> itens = new ArrayList<>(colaboradores.size());
        for (Colaborador c : colaboradores) {
            itens.add(new SelectItem(c.getId(), c.getNome()));
        }
        return itens;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador c) {
        this.colaborador = c;
    }
    
}
