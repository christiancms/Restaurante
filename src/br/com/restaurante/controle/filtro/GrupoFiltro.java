/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.controle.filtro;

import br.com.restaurante.controle.InterfaceFilter;
import br.com.restaurante.dao.GrupoDAO;
import br.com.restaurante.modelo.Grupo;
import java.util.List;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class GrupoFiltro implements InterfaceFilter {

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
