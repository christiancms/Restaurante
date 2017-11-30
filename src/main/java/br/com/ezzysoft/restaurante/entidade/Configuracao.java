package br.com.ezzysoft.restaurante.entidade;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author christian
 */
@Entity
@Table(name = "configuracao")
public class Configuracao  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "comissao")
    private Double percComissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercComissao() {
        return percComissao;
    }

    public void setPercComissao(Double percComissao) {
        this.percComissao = percComissao;
    }
}
