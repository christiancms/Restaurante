package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 * Created by christian on 03/11/17.
 */
public class UnidadeTransporter implements Serializable {

    private long id;
    private String sigla;
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
