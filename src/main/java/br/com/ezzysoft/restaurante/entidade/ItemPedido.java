package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "itensPedido")
@NamedQueries({
        @NamedQuery(name = "ItemPedido.findAll", query = "SELECT o FROM ItemPedido o"),
        @NamedQuery(name = "ItemPedido.findById", query = "SELECT o FROM ItemPedido o WHERE o.id = :id"),
        @NamedQuery(name = "ItemPedido.findByPedido", query = "SELECT o FROM ItemPedido o WHERE o.pedido.id = :pedidoId")
})
public class ItemPedido implements Serializable {

    public static final String ITENSPORPEDIDO = "ItemPedido.findByPedido";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "quantidade")
    private Double quantidade;
    @Column(name = "valor_item")
    private Double valorItem;
    @Column(name = "valor_unit")
    private Double valorUnit;
    //---------------- Produto ----------------
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    //---------------- Pedido ----------------
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Transient
    public Double getValorTotal() {
        return (this.getValorUnit() * this.getQuantidade());
    }

    @Transient
    public boolean isProdutoAssociado() {
        return this.getProduto() != null && this.getProduto().getId() != null;
    }
}
