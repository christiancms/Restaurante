package br.com.ezzysoft.restaurante.entidade;

import org.eclipse.persistence.annotations.*;
import org.eclipse.persistence.annotations.Cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "produto")
@NamedQueries({
        @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
        @NamedQuery(name = "Produto.Grafico", query = "SELECT p FROM Produto p INNER JOIN Marca m ON p.marca.id=m.id"),
        @NamedQuery(name = "Produto.findByGrupo", query = "SELECT p FROM Produto p INNER JOIN Grupo g ON p.grupo.id=g.id WHERE g.id = :grupoId"),
        @NamedQuery(name = "Produto.Cardapio", query = "SELECT p FROM Produto p WHERE p.cardapio = true "),
        @NamedQuery(name = "Produto.ItensCompoeProduto", query = "SELECT p FROM Produto p WHERE p.cardapio = false "),
        @NamedQuery(name = "ProdutoComposto.findAll", query = "SELECT p FROM Produto p WHERE p.composto = true"),
        @NamedQuery(name = "Produto.NomeSemelhante", query = "SELECT p FROM Produto p WHERE p.composto = false AND p.cardapio = false AND p.descricao LIKE :descricao ")
})
public class Produto implements Serializable {

    public static final String GRAFPROXMARCA = "Produto.Grafico";
    public static final String ITENSPORGRUPO = "Produto.findByGrupo";
    public static final String PRODUTOSCOMPOSTO = "ProdutoComposto.findAll";
    public static final String CARDAPIO = "Produto.Cardapio";
    public static final String ITENSCOMPOEPRODUTO = "Produto.ItensCompoeProduto";
    public static final String NOMESEMELHANTE = "Produto.NomeSemelhante";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "descricaoAdc", length = 180)
    private String descAdicional = "";
    @Basic(optional = false)
    @Column(name = "preco_compra")
    private Double precoCompra;
    @Column(name = "preco_venda")
    private Double precoVenda = 0d;
    @Basic(optional = false)
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Column(name = "codigo_barras", length = 20)
    private String codigoBarras = "";
    @Column(name = "percentual_lucro")
    private Double percLucro = 0d;
    @Column(name = "cardapio")
    private boolean cardapio = false;
    @Column(name = "composto")
    private boolean composto = false;
    //---------------- ItensProduto ----------------
    @OneToMany(mappedBy = "produto")
    private List<ItemProduto> itensProduto;
    @Column(name = "versao")
    @Version
    private Integer versao;
    /**
     * Se a associação é opcional.
     * Se definido como falso, uma relação não nula sempre deve existir.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_id", referencedColumnName = "id", updatable = true)
    private Grupo grupo = new Grupo();
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id", nullable = true, referencedColumnName = "id", updatable = true)
    private Marca marca = new Marca();
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", updatable = true)
    private Unidade unidade = new Unidade();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="combinado_produto", joinColumns={@JoinColumn(name="combinado_id")},
            inverseJoinColumns={@JoinColumn(name="produto_id")})
    private List<Combinado> combinados = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", updatable = true)
    private Status status = new Status();

    public Produto() {
    }

    public Produto(String descricao, String descAdicional, Double precoCompra, Double precoVenda, Date dataCadastro, String codigoBarras, Double percLucro, Status status, boolean composto, Grupo grupo, Marca marca, Unidade unidade) {
        this.descricao = descricao;
        this.descAdicional = descAdicional;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.dataCadastro = dataCadastro;
        this.codigoBarras = codigoBarras;
        this.percLucro = percLucro;
        this.status = status;
        this.composto = composto;
        this.grupo = grupo;
        this.marca = marca;
        this.unidade = unidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public String getDescAdicional() {
        return descAdicional;
    }

    public void setDescAdicional(String descAdicional) {
        this.descAdicional = descAdicional;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPercLucro() {
        return percLucro;
    }

    public void setPercLucro(Double percLucro) {
        this.percLucro = percLucro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Combinado> getCombinados() {
        return combinados;
    }

    public void setCombinados(List<Combinado> combinados) {
        this.combinados = combinados;
    }

    public boolean isComposto() {
        return composto;
    }

    public void setComposto(boolean composto) {
        this.composto = composto;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public boolean isCardapio() {
        return cardapio;
    }

    public void setCardapio(boolean cardapio) {
        this.cardapio = cardapio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto[ id=" + id + " ]";
    }

}
