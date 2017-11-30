package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 * Created by christian on 03/11/17.
 */
public class ClienteTransporter implements Serializable {

    private long id;
    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
