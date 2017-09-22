package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.UnidadeDAO;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBUnidade")
@SessionScoped
public class MBUnidade extends CrudBean<Unidade, UnidadeDAO> {

    private UnidadeDAO unidadeDAO;

    @Override
    public UnidadeDAO getDao() {
        if (unidadeDAO == null) {
            unidadeDAO = new UnidadeDAO();
        }
        return unidadeDAO;
    }

    @Override
    public Unidade criarNovaEntidade() {
        return new Unidade();
    }

    public UnidadeDAO getUnidadeDAO() {
        return unidadeDAO;
    }

    public void setUnidadeDAO(UnidadeDAO unidadeDAO) {
        this.unidadeDAO = unidadeDAO;
    }
}
