package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.dao.MarcaDAO;
import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.dao.UnidadeDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.entidade.Marca;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name= "MBProduto")
@SessionScoped
public class MBProduto extends CrudBean<Produto, ProdutoDAO> {

    private ProdutoDAO produtoDAO;
    private final Marca marca = new Marca();
    private final Grupo grupo = new Grupo();
    private final Unidade unidade = new Unidade();
    
    @Override
    public ProdutoDAO getDao() {
        if (produtoDAO == null) {
            produtoDAO = new ProdutoDAO();
        }
        return produtoDAO;
    }

    @Override
    public Produto criarNovaEntidade() {
        return new Produto();
    }

    public List<SelectItem> getUnidades(){
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        List<Unidade> unidades = unidadeDAO.listAll();
        List<SelectItem> itens = new ArrayList<>(unidades.size());
        for (Unidade u : unidades) {
            itens.add(new SelectItem(u.getId(), u.getDescricao()));
        }
        return itens;
    }
    
    public List<SelectItem> getGrupos(){
        GrupoDAO grupoDAO = new GrupoDAO();
        List<Grupo> grupos = grupoDAO.listAll();
        List<SelectItem> itens = new ArrayList<>(grupos.size());
        for (Grupo g : grupos) {
            itens.add(new SelectItem(g.getId(), g.getDescricao()));
        }
        return itens;
    }
    
    public List<SelectItem> getMarcas(){
        MarcaDAO marcaDAO = new MarcaDAO();
        List<Marca> marcas = marcaDAO.listAll();
        List<SelectItem> itens = new ArrayList<>(marcas.size());
        for (Marca m : marcas) {
            itens.add(new SelectItem(m.getId(), m.getDescricao()));
        }
        return itens;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Marca getMarca() {
        return marca;
    }

    public Grupo getGrupo() {
        return grupo;
    }
    
}
