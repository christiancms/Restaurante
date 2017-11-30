package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.ItemProduto;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author christian
 */
@Stateless
public class ProdutoFacade extends AbstractFacade<Produto> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }

    public void salvar(Produto produto) {
        if (produto.getPrecoCompra() != null && produto.getPrecoVenda() != null) {
            double diff = produto.getPrecoVenda() - produto.getPrecoCompra();
            double margem = (diff * 100) / produto.getPrecoCompra();
            produto.setPercLucro(margem);
            super.edit(produto);
        } else {
            super.edit(produto);
        }
    }

    public List<Produto> buscarGrafico() throws ErroSistema {
        return getEntityManager().createNamedQuery(Produto.GRAFPROXMARCA).getResultList();
    }

    public List<Produto> produtoPorGrupo(Long grupoId) {//grupoId
        List<Produto> lista = getEntityManager().createNamedQuery(Produto.ITENSPORGRUPO).setParameter("grupoId", grupoId).getResultList();
        return lista;
    }

    public List<Produto> getProdutosComposto() {
        List<Produto> lista = getEntityManager().createNamedQuery(Produto.PRODUTOSCOMPOSTO).getResultList();
        return lista;
    }

    public List<Produto> getItensProduto(Long produtoId){
        return  getEntityManager().createNamedQuery(ItemProduto.ITENSPORPRODUTO).setParameter("compostoId", produtoId).getResultList();
    }

    public List<Produto> getCardapio(){
        List<Produto> lista = getEntityManager().createNamedQuery(Produto.CARDAPIO).getResultList();
        return lista;
    }

    public List<Produto> getItensCompoeCardapio(){
        List<Produto> lista = getEntityManager().createNamedQuery(Produto.ITENSCOMPOEPRODUTO).getResultList();
        return lista;
    }

    public List<Produto> porNomeSemelhante(String nome){
        List<Produto> lista = getEntityManager().createNamedQuery(Produto.NOMESEMELHANTE).setParameter("descricao", "%"+nome+"%").getResultList();
        return lista;
    }
}
