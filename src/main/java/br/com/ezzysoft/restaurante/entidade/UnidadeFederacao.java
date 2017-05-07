package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "unidadefederacao")
public class UnidadeFederacao implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String descricao;
    @Column
    private String sigla;
    @ManyToOne
    @JoinColumn(name = "PAISID", referencedColumnName = "ID")
    private Pais pais;
    @OneToMany(mappedBy = "uf")
    private List<Cidade> cidades = new ArrayList<>();

    public UnidadeFederacao() {
    }

    public UnidadeFederacao(String descricao, String sigla, Pais pais) {
        this.descricao = descricao;
        this.sigla = sigla;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
