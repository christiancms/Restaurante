package br.com.ezzysoft.restaurante.entidade;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "itensProduto")
@NamedQueries({
        @NamedQuery(name = "ItemProduto.findAll", query = "SELECT o FROM ItemProduto o"),
        @NamedQuery(name = "ItemProduto.findById", query = "SELECT o FROM ItemProduto o WHERE o.id = :id"),
        @NamedQuery(name = "ItemProduto.findByProduto", query = "SELECT o FROM ItemProduto o WHERE o.idProdutoComposto = :compostoId")
})
public class ItemProduto implements Serializable {

    public static final String ITENSPORPRODUTO = "ItemProduto.findByProduto";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "quantidade")
    private Double quantidade;
    //---------------- Produto ----------------
    @ManyToOne(optional = true)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    //---------------- Pedido ----------------
    @Column(name = "produto_composto_id")
    private Long idProdutoComposto;

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

    public Long getIdProdutoComposto() {
        return idProdutoComposto;
    }

    public void setIdProdutoComposto(Long idProdutoComposto) {
        this.idProdutoComposto = idProdutoComposto;
    }
}
