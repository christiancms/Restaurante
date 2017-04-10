package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String descricao;
    @Column
    private double precoCompra;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    @OneToOne
    @JoinColumn(name ="grupo_id")
    private Grupo grupo;
    @OneToOne
    @JoinColumn(name ="marca_id")
    private Marca marca;
    @OneToOne
    @JoinColumn(name ="unidade_id")
    private Unidade unidade;
    

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

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
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
    
}
