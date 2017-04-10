package br.com.ezzysoft.restaurante.entidade;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@Entity
@Table(name = "uf")
public class Uf implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    
}
