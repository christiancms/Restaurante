package br.com.ezzysoft.restaurante.bean.filter;

import br.com.ezzysoft.restaurante.bean.InterfaceFilter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBProdutoFilter")
@SessionScoped
public class MBProdutoFilter implements InterfaceFilter {

    @Override
    public void filterRemove() {
    }

    @Override
    public void filterPrint() {
    }

    @Override
    public void filterFind() {
    }
    
}
