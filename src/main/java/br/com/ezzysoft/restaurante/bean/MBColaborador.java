package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ColaboradorDAO;
import br.com.ezzysoft.restaurante.entidade.Colaborador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBColaborador")
@SessionScoped
public class MBColaborador extends CrudBean<Colaborador, ColaboradorDAO>{

    ColaboradorDAO colaboradorDAO;
    
    @Override
    public ColaboradorDAO getDao() {
        if (colaboradorDAO == null) {
            colaboradorDAO = new ColaboradorDAO();
        }
        return colaboradorDAO;
    }

    @Override
    public Colaborador criarNovaEntidade() {
        return new Colaborador();
    }
    
}
