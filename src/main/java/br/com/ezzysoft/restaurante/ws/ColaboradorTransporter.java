package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 *
 * @author christian
 */
public class ColaboradorTransporter implements Serializable {
 
    private Long idColaborador;
    private String nome;

    public ColaboradorTransporter() {
    }

    public ColaboradorTransporter(Long idColaborador, String nome) {
        this.idColaborador = idColaborador;
        this.nome = nome;
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
