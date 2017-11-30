package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.facade.ProdutoFacade;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 27/11/17.
 */
@ManagedBean(name = "mbProdutoSelect")
@ViewScoped
public class MBProdutoSelect implements Serializable {

    @EJB
    private ProdutoFacade facadeProduto;

    private Long id;
    private String descricao;
    private List<Produto> produtosFiltrados;

    public MBProdutoSelect() {
    }

    public void pesquisar(){
        produtosFiltrados = facadeProduto.porNomeSemelhante(descricao);
    }

    public void abrirDialogo(){
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 370);

        RequestContext.getCurrentInstance().openDialog("/produto/produtoSelect", opcoes, null);
    }

    public void selecionar(Produto produto){
        RequestContext.getCurrentInstance().closeDialog(produto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }
}