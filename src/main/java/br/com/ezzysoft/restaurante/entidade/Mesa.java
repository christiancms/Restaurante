package br.com.ezzysoft.restaurante.entidade;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by christian on 09/12/17.
 */
@Entity
@Table(name = "mesa")
@NamedQueries({
        @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
        @NamedQuery(name = "Mesa.findOcupadas", query = "SELECT m FROM Mesa m WHERE m.livre = false")
})
public class Mesa implements Serializable {

    public static final String MESASOCUPADAS = "Mesa.findOcupadas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "livre")
    private boolean livre = true;

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

    public boolean isLivre() {
        return livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mesa mesa = (Mesa) o;

        if (livre != mesa.livre) return false;
        if (id != null ? !id.equals(mesa.id) : mesa.id != null) return false;
        return descricao != null ? descricao.equals(mesa.descricao) : mesa.descricao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (livre ? 1 : 0);
        return result;
    }
}
