/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.controle.filtro;

import br.com.restaurante.controle.InterfaceFilter;
import br.com.restaurante.dao.UnidadeDAO;
import br.com.restaurante.modelo.Unidade;
import br.com.restaurante.visual.JFIUnidade;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class UnidadeFiltro implements InterfaceFilter {

    @Override
    public void filterRemove() {
    }

    @Override
    public void filterPrint() {
    }

    @Override
    public void filterFind() {
    }

    public void fillGrid() {
        DefaultTableModel model;

    }
    
    public List<Unidade> loadCmbUnidade(){
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        return unidadeDAO.listAll();
    }
}
