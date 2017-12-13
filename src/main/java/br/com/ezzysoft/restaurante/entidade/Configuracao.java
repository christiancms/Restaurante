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
    @Column(name = "token")
    private String token;
    @Column(name = "serverkey")
    private String serverkey;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServerkey() {
        return serverkey;
    }

    public void setServerkey(String serverkey) {
        this.serverkey = serverkey;
    }
}
