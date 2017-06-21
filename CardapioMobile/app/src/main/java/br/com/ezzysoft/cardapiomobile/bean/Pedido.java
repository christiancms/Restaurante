package br.com.ezzysoft.cardapiomobile.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by christian on 21/06/17.
 */

public class Pedido implements Serializable {

    private Integer id;
    private Integer pedidoerpId;
    private Integer representanteId;
    private Double valorTotal;
    private String formaPgto;
    private String obs;
    private Double descontoTotal;
    private Integer idCliente;
    private Boolean status;
    //@JsonProperty("itensPedido")
    private List<ItemPedido> itensPedido = new ArrayList<>();
    private Date ultAtt;

    public Pedido() {
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(Double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getPedidoerpId() {
        return pedidoerpId;
    }

    public void setPedidoerpId(Integer pedidoerpId) {
        this.pedidoerpId = pedidoerpId;
    }

    public Integer getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Integer representanteId) {
        this.representanteId = representanteId;
    }

    public Date getUltAtt() {
        return ultAtt;
    }

    public void setUltAtt(Date ultAtt) {
        this.ultAtt = ultAtt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
