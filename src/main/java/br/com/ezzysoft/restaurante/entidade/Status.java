package br.com.ezzysoft.restaurante.entidade;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by christian on 31/10/17.
 */
@Entity
@Table(name = "status")
@NamedQueries({
        @NamedQuery(name = "Status.findPedidoAll", query = "SELECT o FROM Status o WHERE o.classe = 'Pedido'"),
        @NamedQuery(name = "Status.findPedidoIndice", query = "SELECT o FROM Status o WHERE o.classe = 'Pedido' AND o.indice = :statusIndice"),
        @NamedQuery(name = "Status.findProdutoAll", query = "SELECT o FROM Status o WHERE o.classe = 'Produto'"),
        @NamedQuery(name = "Status.findProdutoIndice", query = "SELECT o FROM Status o WHERE o.classe = 'Produto' AND o.indice = :statusIndice")
})
public class Status implements Serializable {

    public static final String ENUMPEDIDO = "Status.findPedidoAll";
    public static final String INDICEENUMPEDIDO = "Status.findPedidoIndice";
    public static final String ENUMPRODUTO = "Status.findProdutoAll";
    public static final String INDICEENUMPRODUTO = "Status.findProdutoIndice";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "indice")
    private int indice;
    @Column(name = "classe")
    private String classe;
    @Column(name = "opcao")
    private String opcao;
    @Column(name = "versao")
    @Version
    private Integer versao;

    public Status() {
    }

    public Status(String classe, int indice, String opcao, Integer versao) {
        this.classe = classe;
        this.indice = indice;
        this.opcao = opcao;
        this.versao = versao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
}
