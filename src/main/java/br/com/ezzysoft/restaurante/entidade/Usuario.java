package br.com.ezzysoft.restaurante.entidade;

/**
 *
 * @author christian
 */
import br.com.ezzysoft.restaurante.dao.UsuarioDAO;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.auth", query = "SELECT u FROM Usuario  u WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u "),
        @NamedQuery(name = "Usuario.findAllOrder", query = "SELECT u FROM Usuario u ORDER BY u.username"),
        @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")})
public class Usuario implements Serializable {

    public static final String AUTH = "Usuario.auth";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "usuario", unique = true)
    private String username;
    @Column(name = "senha")
    private String password;
    @Column(name = "versao")
    @Version
    private Integer versao;
    @Column(name = "nivel")
    private Integer nivel = 2;

    public Usuario() {
    }

    public Usuario(String username) {
        this.username = username;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = convertStringToMd5(senha.trim());
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    // Verifica se usuário existe ou se pode logar
    public boolean isUsuarioReadyToLogin(String usuario, String senha) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.validaAutenticacao(usuario, senha);
    }

    private String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            //Instanciamos o nosso HASH MD5, poderíamos usar outro como
            //SHA, por exemplo, mas optamos por MD5.
            mDigest = MessageDigest.getInstance("MD5");

            //Convert a String valor para um array de bytes em MD5
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

            //Convertemos os bytes para hexadecimal, assim podemos salvar
            //no banco para posterior comparação se senhas
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public boolean isAdm(){
        return 0 == nivel;
    }
    public boolean isGerente(){
        return 1 == nivel;
    }
    public boolean isUsuario(){
        return 2 == nivel;
    }
}
