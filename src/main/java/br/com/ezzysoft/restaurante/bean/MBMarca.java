/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.MarcaDAO;
import br.com.ezzysoft.restaurante.entidade.Marca;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBMarca")
@SessionScoped
public class MBMarca extends CrudBean<Marca, MarcaDAO> {

    private MarcaDAO marcaDAO;

    @Override
    public MarcaDAO getDao() {
        if (marcaDAO == null) {
            marcaDAO = new MarcaDAO();
        }
        return marcaDAO;
    }

    @Override
    public Marca criarNovaEntidade() {
        return new Marca();
    }

}
