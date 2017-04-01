/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.controle.filtro;

import br.com.restaurante.controle.InterfaceFilter;
import br.com.restaurante.dao.MarcaDAO;
import br.com.restaurante.modelo.Marca;
import java.util.List;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class MarcaFiltro implements InterfaceFilter {

    @Override
    public void filterRemove() {
    }

    @Override
    public void filterPrint() {
    }

    @Override
    public void filterFind() {
    }
    
    public List<Marca> loadCmbMarca() {
        MarcaDAO marcaDAO = new MarcaDAO();
        return marcaDAO.listAll();
    }
    
}
