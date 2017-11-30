package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 * Created by christian on 03/11/17.
 */
public class UsuarioTransporter implements Serializable {

    private long id;
    private String usuario;
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
