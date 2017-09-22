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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
 
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column 
    private String userName;
    @Column 
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
                      for (byte b : valorMD5){
                             sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
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
                      
}