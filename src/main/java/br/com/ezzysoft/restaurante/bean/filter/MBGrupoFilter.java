package br.com.ezzysoft.restaurante.bean.filter;

import br.com.ezzysoft.restaurante.bean.*;
import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBGrupoFilter")
@SessionScoped
public class MBGrupoFilter implements InterfaceFilter {

    @Override
    public void filterRemove() {
    }

    @Override
    public void filterPrint() {
    }

    @Override
    public void filterFind() {
    }

    public List<Grupo> loadCmbGrupo() {
        GrupoDAO gdao = new GrupoDAO();
        return gdao.listAll();
    }
}
