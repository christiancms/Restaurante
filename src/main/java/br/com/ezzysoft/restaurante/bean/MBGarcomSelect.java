package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.Colaborador;
import br.com.ezzysoft.restaurante.facade.ColaboradorFacade;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 11/12/17.
 */
@ManagedBean(name = "mbGarcomSelect")
@SessionScoped
public class MBGarcomSelect implements Serializable {

    @EJB
    private ColaboradorFacade facade;

    private String nome;
    private Long id;
    private List<Colaborador> garconsFiltrados;

    public MBGarcomSelect() {
    }

    public void pesquisar(){
        garconsFiltrados = facade.porNomeSemelhante(nome);
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 370);

        RequestContext.getCurrentInstance().openDialog("/colaborador/colaboradorSelect", opcoes, null);
    }

    public void selecionar(Colaborador colaborador) {
        RequestContext.getCurrentInstance().closeDialog(colaborador);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Colaborador> getGarconsFiltrados() {
        return garconsFiltrados;
    }
}
