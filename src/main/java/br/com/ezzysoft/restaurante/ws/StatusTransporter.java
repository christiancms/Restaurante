package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 * Created by christian on 03/11/17.
 */
public class StatusTransporter implements Serializable {

    private long id;
    private long indice;
    private String classe;
    private String opcao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIndice() {
        return indice;
    }

    public void setIndice(long indice) {
        this.indice = indice;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }
}
