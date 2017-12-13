package br.com.ezzysoft.restaurante.facade;

import br.com.ezzysoft.restaurante.entidade.ItemProduto;
import br.com.ezzysoft.restaurante.entidade.ItemProdutoDecorator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by christian on 30/11/17.
 */
@Stateless
public class ItemProdutoFacade extends AbstractFacade<ItemProduto> {

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemProdutoFacade() {
        super(ItemProduto.class);
    }

    public List<ItemProduto> getItensProdutoComposto(Long idComposto){
        return getEntityManager().createQuery("select o from ItemProduto o WHERE o.idProdutoComposto = :compostoId", ItemProduto.class)
                .setParameter("compostoId",idComposto).getResultList();
    }
}
