package br.com.ezzysoft.restaurante.ws;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author christian
 */
public class ProdutoTransporter implements Serializable {
    
    private Long idProduto;
    private String descricao;
    private Double preco;
    private Date dataCadastro;
    private Long grupoId;
    private Long marcaId;
    private Long unidadeId;
    private String nomeGrupo;

    public ProdutoTransporter() {
    }

    public ProdutoTransporter(Long idProduto, String descricao, Double preco, Date dataCadastro, Long grupoId, Long marcaId, Long unidadeId, String nomeGrupo) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.grupoId = grupoId;
        this.marcaId = marcaId;
        this.unidadeId = unidadeId;
        this.nomeGrupo = nomeGrupo;
    }
       
    public ProdutoTransporter(Long idProduto, String descricao, Double preco, Date dataCadastro, Long grupoId, Long marcaId, Long unidadeId) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.grupoId = grupoId;
        this.marcaId = marcaId;
        this.unidadeId = unidadeId;
    }
    
    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }
    
    
}
