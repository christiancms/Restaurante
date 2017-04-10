package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "mesa")
    private Integer mesa;
    @Column(name = "garcom")
    private Integer numeroGarcom;
//---------------- ItensPedido ----------------
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Integer getNumeroGarcom() {
        return numeroGarcom;
    }

    public void setNumeroGarcom(Integer numeroGarcom) {
        this.numeroGarcom = numeroGarcom;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
   
    
}
