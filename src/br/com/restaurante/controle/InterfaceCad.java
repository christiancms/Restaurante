/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurante.controle;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public interface InterfaceCad {
    
    public void cadNew();
    public void cadOpen();
    public void cadRemove();
    public void cadSave();
    public boolean cadValidate();
}
