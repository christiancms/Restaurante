package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Produto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        if (produto.getPercLucro() == 0d && produto.getPrecoCompra() != null && produto.getPrecoVenda() != null) {
            double diff = produto.getPrecoVenda() - produto.getPrecoCompra();
            double margem = (diff * 100) / produto.getPrecoCompra();
            produto.setPercLucro(margem);
            super.edit(produto);
        } else {
            super.edit(produto);
        }
    }

}
