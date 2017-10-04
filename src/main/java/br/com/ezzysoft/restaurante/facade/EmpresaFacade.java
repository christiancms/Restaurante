package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Empresa;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author christian
 */
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
}
