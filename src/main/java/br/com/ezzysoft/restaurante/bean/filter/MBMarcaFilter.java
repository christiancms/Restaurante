package br.com.ezzysoft.restaurante.bean.filter;

import br.com.ezzysoft.restaurante.bean.InterfaceFilter;
import br.com.ezzysoft.restaurante.dao.MarcaDAO;
import br.com.ezzysoft.restaurante.entidade.Marca;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Named("MBMarcaFilter")
@SessionScoped
public class MBMarcaFilter implements InterfaceFilter, Serializable {

    @Override
    public void filterRemove() {
    }

    @Override
    public void filterPrint() {
    }

    @Override
    public void filterFind() {
    }
    
     public List<Marca> fillGrid() {
        MarcaDAO marcaDAO = new MarcaDAO();
        return marcaDAO.listAll();
    }
    
    public List<Marca> loadCmbMarca() {
        MarcaDAO marcaDAO = new MarcaDAO();
        return marcaDAO.listAll();
    }
    
}
