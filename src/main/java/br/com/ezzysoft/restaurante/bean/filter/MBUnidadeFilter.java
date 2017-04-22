package br.com.ezzysoft.restaurante.bean.filter;

import br.com.ezzysoft.restaurante.bean.InterfaceFilter;
import br.com.ezzysoft.restaurante.dao.UnidadeDAO;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBUnidadeFilter")
@SessionScoped
public class MBUnidadeFilter implements InterfaceFilter {

    @Override
    public void filterRemove() {
    }

    @Override
    public void filterPrint() {
    }

    @Override
    public void filterFind() {
    }

    public List<Unidade> fillGrid() {
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        return unidadeDAO.listAll();

    }
    
    public List<Unidade> loadCmbUnidade(){
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        return unidadeDAO.listAll();
    }
}
