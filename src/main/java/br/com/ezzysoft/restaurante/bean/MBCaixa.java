package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.entidade.Produto;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author christian
 */
@ManagedBean(name = "MBCaixa")
@SessionScoped
public class MBCaixa {
    
    private ProdutoDAO produtoDAO;
    private Date dataAtual = new Date();
    private Integer quantidade;
 
    public void relogio(){
        dataAtual = Calendar.getInstance(new Locale("BR")).getTime();
    }
    
    public void fecharVenda(){
        
    }
    
    public void cancelarProduto(){
        
    }
    
    public void cancelarVenda(){
        
    }
    
    public void fecharCaixa(){
        
    }
    
    public Integer init(){
        return quantidade = 1;
    }
    public void onLoad(){
        
    }
    
    public Produto findProduto(Long id){
        Produto p = new Produto();
        return p;
    }
    
    
}