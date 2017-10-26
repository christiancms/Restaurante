package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
                    .setParameter("username", username).setParameter("password", password)
                    .getSingleResult();
            if (usr != null) {
                return usr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
