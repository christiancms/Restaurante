package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "marca")
@NamedQueries({
        @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m "),
        @NamedQuery(name = "Marca.findAllOrder", query = "SELECT m FROM Marca m ORDER BY m.descricao"),
        @NamedQuery(name = "Marca.findById", query = "SELECT m FROM Marca m WHERE  m.id = :id")})
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao")
    private String descricao;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Marca() {
    }

    public Marca(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Marca[ id=" + id + " ]";
    }


}
