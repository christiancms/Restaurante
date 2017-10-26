package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.entidade.*;
import br.com.ezzysoft.restaurante.facade.*;
import br.com.ezzysoft.restaurante.util.Util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.faces.event.ActionEvent;

/**
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBConfig")
@SessionScoped
public class MBConfig implements Serializable {

    @EJB
    private ClienteFacade facadeCliente;
    @EJB
    private UsuarioFacade facadeUsuario;
    @EJB
    private ColaboradorFacade facadeColaborador;
    @EJB
    private GrupoFacade facadeGrupo;
    @EJB
    private MarcaFacade facadeMarca;
    @EJB
    private UnidadeFacade facadeUnidade;
    @EJB
    private ProdutoFacade facadeProduto;

    private String username;
    private String password;
    Util util = new Util();

    public MBConfig() {
    }

    public void criarRegistros() {
        if (util.loginMaster(getUsername(), getPassword())) {

            Cliente cliente = facadeCliente.find(1l);
            if (cliente == null){
                cliente = new Cliente("Cliente Padrão");
                facadeCliente.create(cliente);
            }
            Usuario usuario = facadeUsuario.find(1l);
            if (usuario == null) {
                usuario = new Usuario("admin", "admin");
                facadeUsuario.create(usuario);
            }
            Colaborador colaborador = facadeColaborador.find(1l);
            if (colaborador == null) {
                colaborador = new Colaborador("Colaborador 01", usuario);
                facadeColaborador.create(colaborador);
            }
            Grupo grupo = facadeGrupo.find(1l);
            if (grupo == null) {
                grupo = new Grupo("Grupo 01");
                facadeGrupo.create(grupo);
            }
            Marca marca =facadeMarca.find(1l);
            if (marca == null) {
                marca = new Marca("Marca 01");
                facadeMarca.create(marca);
            }
            Unidade unidade = facadeUnidade.find(1l);
            if (unidade == null) {
                unidade = new Unidade("Unidade", "un");
                facadeUnidade.create(unidade);
            }
            Produto produto = facadeProduto.find(1l);
            if (produto == null) {
                produto = new Produto(
                        "Produto padrão", "Produto do menu Config", 10d, 15d, new Date(),
                        "1234567890", 50.0d, Produto.Status.ATIVO, false, grupo, marca, unidade);
                facadeProduto.create(produto);
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
