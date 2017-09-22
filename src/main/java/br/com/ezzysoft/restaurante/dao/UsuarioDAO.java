package br.com.ezzysoft.restaurante.dao;

import br.com.ezzysoft.restaurante.entidade.Usuario;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author christian
 */
public class UsuarioDAO implements CrudDAO<Usuario>{

    EntityManager em;
    
    @Override
    public void salvar(Usuario entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Usuario entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscar() throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EzzysoftPU");
        return factory.createEntityManager();
    }

    @Override
    public Usuario findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean validaAutenticacao(String usuario, String senha){
        em = getEM();
        try{
            em.getTransaction().begin();
            Query q = em.createQuery(" SELECT u FROM Usuario u WHERE u.userName = :login ");
            q.setParameter("login",usuario);
            return !q.getResultList().isEmpty();
        } finally {
            em.close();
        }
    }
    
}
