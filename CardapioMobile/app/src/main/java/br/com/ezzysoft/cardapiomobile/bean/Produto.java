package br.com.ezzysoft.cardapiomobile.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by christian on 21/06/17.
 */

public class Produto implements Serializable {

    private Integer id;
    private String descricao;
    private String precoVenda;
    private String saldoEstoq;
    private String observacao;
    private String unidade;
    private String marca;
    private Date ultAtt;

    public Produto() {
    }

    public Produto(Integer id, String descricao, String precoVenda) {
        this.id = id;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
    }

    public Produto(Integer id, String descricao, String observacao, String unidade, String marca, String precoVenda, String saldoEstoq) {
        this.id = id;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.saldoEstoq = saldoEstoq;
        this.observacao = observacao;
        this.unidade = unidade;
        this.marca = marca;
    }

    public String getSaldoEstoq() {
        return saldoEstoq;
    }

    public void setSaldoEstoq(String saldoEstoq) {
        this.saldoEstoq = saldoEstoq;
    }

    public Date getUltAtt() {
        return ultAtt;
    }

    public void setUltAtt(Date ultAtt) {
        this.ultAtt = ultAtt;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPrecoVenda() { return precoVenda; }

    public void setPrecoVenda(String precoVenda) { this.precoVenda = precoVenda; }

}
