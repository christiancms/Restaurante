package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;

/**
 *
 * @author christian
 */
public class ItemTransporter implements Serializable {

    private Long idItem;
    private Long produtoId;
    private Double quantidade;
    private Long pedidoId;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

}
