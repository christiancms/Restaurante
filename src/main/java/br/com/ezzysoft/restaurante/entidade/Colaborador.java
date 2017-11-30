package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "colaborador")
@NamedQueries({
    @NamedQuery(name = "Colaborador.findAll", query = "SELECT c FROM Colaborador c"),
    @NamedQuery(name = "Colaborador.findById", query = "SELECT c FROM Colaborador c WHERE c.id = :id")})
public class Colaborador extends Usuario implements Serializable {

    public static final String FINDBYID = "Colaborador.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "appToken")
    private String appToken;
//    @OneToOne
//    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
//    private Usuario usuario;
    @Column(name = "versao")
    @Version
    private Integer versao;

    public Colaborador() {
    }

    public Colaborador(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Colaborador(String username, String password, String nome) {
        super(username, password);
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Colaborador{" + "nome=" + nome + '}';
    }

}
