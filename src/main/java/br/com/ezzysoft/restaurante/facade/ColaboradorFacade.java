package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Colaborador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author christian
 */
@Stateless
public class ColaboradorFacade extends AbstractFacade<Colaborador> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColaboradorFacade() {
        super(Colaborador.class);
    }

    public List<Colaborador> porNomeSemelhante(String nome){
        List<Colaborador> lista = getEntityManager()
                .createNamedQuery(Colaborador.NOMESEMELHANTE)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
        return lista;
    }
}
