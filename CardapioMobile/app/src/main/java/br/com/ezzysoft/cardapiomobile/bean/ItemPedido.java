package br.com.ezzysoft.cardapiomobile.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by christian on 21/06/17.
 */

public class ItemPedido implements Serializable {

    private Integer id;
    private Integer idPedido;
    private Double qtdeProduto;
    private Double vlrUnitProduto;
    private Double desconto;
    private Integer idProduto;
    private Integer codOpId;
    private Date ultAtt;

    public ItemPedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQtdeProduto() {
        return qtdeProduto;
    }

    public void setQtdeProduto(Double qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    public Double getVlrUnitProduto() {
        return vlrUnitProduto;
    }

    public void setVlrUnitProduto(Double vlrUnitProduto) {
        this.vlrUnitProduto = vlrUnitProduto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getCodOpId() {
        return codOpId;
    }

    public void setCodOpId(Integer codOpId) {
        this.codOpId = codOpId;
    }

    public Date getUltAtt() {
        return ultAtt;
    }

    public void setUltAtt(Date ultAtt) {
        this.ultAtt = ultAtt;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", idProduto=" + idProduto +
                ", qtdeProduto=" + qtdeProduto +
                ", vlrUnitProduto=" + vlrUnitProduto +
                ", desconto=" + desconto +
                '}';
    }
}
