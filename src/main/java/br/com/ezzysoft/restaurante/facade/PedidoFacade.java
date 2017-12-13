package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * @author christian
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }

    @Override
    public List<Pedido> findAll() {
        return getEntityManager().createNamedQuery(Pedido.PEDIDOSFINDALL).getResultList();
    }

    public List<Pedido> getPedidoAtual(Date dataAtual){
     return getEntityManager().createNamedQuery(Pedido.LISTPEDIDOSATUAL).setParameter("dataAtual", dataAtual).getResultList();
    }

    public List<ItemPedido> getItensPedido(Long pedidoId) {
        return getEntityManager().createNamedQuery(ItemPedido.ITENSPORPEDIDO).setParameter("pedidoId", pedidoId).getResultList();
    }

    public List<Pedido> buscarGrafico() throws ErroSistema {
        return getEntityManager().createNamedQuery(Pedido.GRAFPEDXSTATUS).getResultList();
    }

    public List<Pedido> porStatus(String opcao) {
        List<Pedido> lista = getEntityManager()
                .createNamedQuery(Pedido.PORSTATUS)
                .setParameter("opcao", opcao).getResultList();
        return lista;
    }

    public List<Pedido> androidLista() {
        List<Pedido> lista = getEntityManager()
                .createNamedQuery(Pedido.PEDIDOSFINDALL)
                .getResultList();
        return lista;
    }

}
