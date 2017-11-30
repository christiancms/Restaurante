package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.Configuracao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by christian on 28/11/17.
 */
@Stateless
public class ConfiguracaoFacade extends AbstractFacade<Configuracao> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfiguracaoFacade() {
        super(Configuracao.class);
    }

}
