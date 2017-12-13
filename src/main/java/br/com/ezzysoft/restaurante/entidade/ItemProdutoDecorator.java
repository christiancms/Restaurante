package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;

/**
 * Created by christian on 01/12/17.
 */
public class ItemProdutoDecorator implements Serializable {

    private Produto produto;
    private Long pCompostoId;
    private Double quantidade;
    private Double valorCompra;
    private Double valorVenda;
    private Double percLucro;
    private String pDescricao;

    public ItemProdutoDecorator(Produto produto, Long pCompostoId, Double quantidade, Double valorCompra, Double valorVenda, Double percLucro, String pDescricao) {
        this.produto = produto;
        this.pCompostoId = pCompostoId;
        this.quantidade = quantidade;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.percLucro = percLucro;
        this.pDescricao = pDescricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getpCompostoId() {
        return pCompostoId;
    }

    public void setpCompostoId(Long pCompostoId) {
        this.pCompostoId = pCompostoId;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getPercLucro() {
        return percLucro;
    }

    public void setPercLucro(Double percLucro) {
        this.percLucro = percLucro;
    }

    public String getpDescricao() {
        return pDescricao;
    }

    public void setpDescricao(String pDescricao) {
        this.pDescricao = pDescricao;
    }
}
