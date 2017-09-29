package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.dao.MarcaDAO;
import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.dao.UnidadeDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.entidade.Marca;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.entidade.Unidade;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBProduto")
@SessionScoped
public class MBProduto extends CrudBean<Produto, ProdutoDAO> {

    private ProdutoDAO produtoDAO;
    private Marca marca = new Marca();
    private Grupo grupo = new Grupo();
    private Unidade unidade = new Unidade();

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

    public List<SelectItem> getUnidades() throws ErroSistema {
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        List<Unidade> unidades = unidadeDAO.buscar();
        List<SelectItem> itens = new ArrayList<>(unidades.size());
        for (Unidade u : unidades) {
            itens.add(new SelectItem(u.getId(), u.getSigla()));
        }
        return itens;
    }

    public List<SelectItem> getGrupos() throws ErroSistema {
        GrupoDAO grupoDAO = new GrupoDAO();
        List<Grupo> grupos = grupoDAO.buscar();
        List<SelectItem> itens = new ArrayList<>(grupos.size());
        for (Grupo g : grupos) {
            itens.add(new SelectItem(g.getId(), g.getDescricao()));
        }
        return itens;
    }

    public List<SelectItem> getMarcas() throws ErroSistema {
        MarcaDAO marcaDAO = new MarcaDAO();
        List<Marca> marcas = marcaDAO.buscar();
        List<SelectItem> itens = new ArrayList<>(marcas.size());
        for (Marca m : marcas) {
            itens.add(new SelectItem(m.getId(), m.getDescricao()));
        }
        return itens;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade u) {
        this.unidade = u;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca m) {
        this.marca = m;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo g) {
        this.grupo = g;
    }

    public void onLoad() {
    }
//    
    public String init(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(new Date());
    }
}
