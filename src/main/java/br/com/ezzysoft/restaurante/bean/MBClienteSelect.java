package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.Cliente;
import br.com.ezzysoft.restaurante.facade.ClienteFacade;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 28/11/17.
 */
@ManagedBean(name = "mbClienteSelect")
@SessionScoped
public class MBClienteSelect implements Serializable{

    @EJB
    private ClienteFacade facade;


    private String nome;
    private Long id;
    private List<Cliente> clientesFiltrados;

    public MBClienteSelect() {
    }

    public void pesquisar() {
        clientesFiltrados = facade.porNomeSemelhante(nome);
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 370);

        RequestContext.getCurrentInstance().openDialog("/cliente/clienteSelect", opcoes, null);
    }

    public void selecionar(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }
}
