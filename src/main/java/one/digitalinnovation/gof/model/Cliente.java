package one.digitalinnovation.gof.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Cliente {

    //Essa classe representa a entidade cliente,
    //que possui como atributos id, nome e endereco.
    //Além disso tem seus métodos toString, getters
    //e setters.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String nome;
    // https://www.baeldung.com/hibernate-one-to-many
    // Cada cliente tem um endereço, mas 
    // cada endereço pode ter vários clientes
    @ManyToOne
    private Endereco endereco;

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
