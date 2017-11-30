package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.*;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "pedido")
@NamedQueries({
        @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p ORDER BY p.id desc "),
        @NamedQuery(name = "Pedido.findAllColaborador", query = "SELECT p FROM Pedido p INNER JOIN Colaborador c ON p.colaborador.id = c.id"),
        @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE  p.id = :id"),
        @NamedQuery(name = "Pedido.listItens", query = "SELECT  p FROM Pedido p INNER JOIN ItemPedido ip ON p.id=ip.pedido.id"),
        @NamedQuery(name = "Pedido.listItensByPedido", query = "SELECT  p FROM Pedido p INNER JOIN ItemPedido ip ON p.id=ip.pedido.id WHERE p.id = :pedidoId"),
        @NamedQuery(name = "Pedido.findByDate", query = "SELECT p FROM Pedido  p INNER JOIN ItemPedido ip ON p.id=ip.pedido.id WHERE p.dataPedido = :dataAtual ORDER BY p.id desc"),
        @NamedQuery(name = "Pedido.grafico", query = "SELECT p FROM Pedido p INNER JOIN Status s ON p.status.id=s.id GROUP BY s.opcao, p.id")
        })
public class Pedido implements Serializable {

    public static final String LISTITENSPEDIDO = "Pedido.listItens";
    public static final String LISTPEDIDOSATUAL = "Pedido.findByDate";
    public static final String PEDIDOSFINDALL = "Pedido.findAll";
    public static final String GRAFPEDXSTATUS = "Pedido.grafico";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    @Column(name = "hora_pedido")
    @Temporal(TemporalType.TIME)
    private Date horaPedido;
    @Column(name = "descricao")
    private String descricao;
    @ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private Colaborador colaborador = new Colaborador();
    @Column(name = "mesa")
    private Integer mesa;
    @Column(name = "total_pedido")
    private Double totalPedido = 0d;
    @Column(name = "total_produtos")
    private Double totalProdutos = 0d;
    @Column(name = "total_servico")
    private Double totalServico = 0d;
    private FormaPagamento formaPagamento;

//---------------- ItensPedido ----------------
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;
    @Column(name = "versao")
    @Version
    private Integer versao;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", updatable = true)
    private Status status = new Status();
    

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Double getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(Double totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public Double getTotalServico() {
        return totalServico;
    }

    public void setTotalServico(Double totalServico) {
        this.totalServico = totalServico;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public enum FormaPagamento {

        DINHEIRO("Dinheiro"),
        CARTAO_CREDITO("Cartão de crédito"),
        CARTAO_DEBITO("Cartão de débito"),
        CHEQUE("Cheque"),
        BOLETO_BANCARIO("Boleto bancário"),
        DEPOSITO_BANCARIO("Depósito bancário");

        private String descricao;

        FormaPagamento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

    }
}