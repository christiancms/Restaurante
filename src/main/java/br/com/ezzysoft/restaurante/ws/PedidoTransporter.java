package br.com.ezzysoft.restaurante.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author christian
 */
public class PedidoTransporter  implements Serializable {

    private Long idPedido;
    private String dataPedido;
    private String horaPedido;
    private String descricao;
    private Long clienteId;
    private Long colaboradorId;
    private Integer mesa;
    @JsonProperty("itensPedido")
    private List<ItemTransporter> itensTransporter = new ArrayList<>();


    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public List<ItemTransporter> getItensTransporter() {
        return itensTransporter;
    }

    public void setItensTransporter(List<ItemTransporter> itensTransporter) {
        this.itensTransporter = itensTransporter;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

}
