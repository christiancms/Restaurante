package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean
@SessionScoped
public class GrupoBean extends CrudBean<Grupo, GrupoDAO> {

    private GrupoDAO grupoDAO;

    @Override
    public GrupoDAO getDao() {
        if (grupoDAO == null) {
            grupoDAO = new GrupoDAO();
        }
        return grupoDAO;
    }

    @Override
    public Grupo criarNovaEntidade() {
        return new Grupo();
    }

}
