package br.com.ezzysoft.restaurante.entidade;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by christian on 22/10/17.
 */
@Entity
@Table(name = "itensProdutoComposto")
public class ItemProdutoComposto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "quantidade")
    private Double quantidade;
    //---------------- Produto ----------------
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    //---------------- ProdutoComposto ----------------
    @ManyToOne
    @JoinColumn(name = "produtocomposto_id")
    private ProdutoComposto produtoComposto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoComposto getProdutoComposto() {
        return produtoComposto;
    }

    public void setProdutoComposto(ProdutoComposto produtoComposto) {
        this.produtoComposto = produtoComposto;
    }
}
