package br.com.ezzysoft.restaurante.facade;

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
        if (produto.getPercLucro() == 0d && produto.getPrecoCompra() != null && produto.getPrecoVenda() != null) {
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

}
