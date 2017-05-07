package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Christian Medeiros
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "nome_razsoc")
    private String nomeRazsoc;
    @Column(name = "sobrenome_fantasia")
    private String sobrenomeFantasia;
    @Column(name = "aniver_abertura")
    @Temporal(TemporalType.DATE)
    private Date aniverAbertura;
    @Column
    private String telefone;
    @Column
    private String celular;
    @Column
    private String email;
    @Column(name = "tipo_pessoa", length = 1)
    private String tipoPessoa; // Pessoa Física ou Pessoa Jurídica
    @Column(name = "fisica_juridica")
    private String fisicaJuridica;
    @Column(name = "docrg_insest")
    private String docrgInsest;
    @Column(length = 1)
    private String categoria; // Cliente, Fornecedor, Transportadora, Outros
    @Column(length = 500)
    private String observacao;
    //---------------- Endereço ----------------
    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRazsoc() {
        return nomeRazsoc;
    }

    public void setNomeRazsoc(String nomeRazsoc) {
        this.nomeRazsoc = nomeRazsoc;
    }

    public String getSobrenomeFantasia() {
        return sobrenomeFantasia;
    }

    public void setSobrenomeFantasia(String sobrenomeFantasia) {
        this.sobrenomeFantasia = sobrenomeFantasia;
    }

    public Date getAniverAbertura() {
        return aniverAbertura;
    }

    public void setAniverAbertura(Date aniverAbertura) {
        this.aniverAbertura = aniverAbertura;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getFisicaJuridica() {
        return fisicaJuridica;
    }

    public void setFisicaJuridica(String fisicaJuridica) {
        this.fisicaJuridica = fisicaJuridica;
    }

    public String getDocrgInsest() {
        return docrgInsest;
    }

    public void setDocrgInsest(String docrgInsest) {
        this.docrgInsest = docrgInsest;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
