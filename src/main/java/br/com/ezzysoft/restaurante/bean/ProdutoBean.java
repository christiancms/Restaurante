package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.entidade.Produto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean
@SessionScoped
public class ProdutoBean extends CrudBean<Produto, ProdutoDAO> {

    private ProdutoDAO produtoDAO;

    @Override
    public ProdutoDAO getDao() {
        if (produtoDAO == null) {
            produtoDAO = new ProdutoDAO();
        }
        return produtoDAO;
    }

    @Override
    public Produto criarNovaEntidade() {
        return new Produto();
    }

}
