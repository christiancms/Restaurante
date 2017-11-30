package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by christian on 14/10/17.
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario loginControl(String username, String password) {
        Usuario usr = null;
        try {
            usr = getEntityManager().createNamedQuery(Usuario.AUTH, Usuario.class)
                    .setParameter("username", username).setParameter("password", crypt(password))
                    .getSingleResult();
            if (usr != null) {
                return usr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String crypt(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String s = hash.toString(16);
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        //Log.getLog().getLogger(Usuario.class).log(Level.FINEST, String.format("Senha Criptografada: %s",s));
        return s;
    }
}
