package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 *
 * @author christian
 */
public class ColaboradorTransporter implements Serializable {
 
    private Long idColaborador;
    private String nome;
    private Long usuarioId;

    public ColaboradorTransporter() {
    }

    public ColaboradorTransporter(Long idColaborador, String nome) {
        this.idColaborador = idColaborador;
        this.nome = nome;
    }

    public ColaboradorTransporter(Long idColaborador, String nome, Long usuarioId) {
        this.idColaborador = idColaborador;
        this.nome = nome;
        this.usuarioId = usuarioId;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
