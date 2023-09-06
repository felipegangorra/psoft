package br.edu.ufcg.computacao.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "enderecos")
public class Endereco {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true,nullable = false,name = "pk_id_endereco")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "fk_id_logradouro",nullable = false)
    private Logradouro logradouro;

    @JsonProperty("numero_endereco")
    @Column(nullable = false,name = "numero_endereco")
    private Integer numero;

    @JsonProperty("complemento_endereco")
    @Column(nullable = false,name = "complemento_endereco")
    private String complemento;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Endereco endereco)) return false;
        if (!this.getNumero().equals(endereco.getNumero())) return false;
        if (!this.getComplemento().equals(endereco.getComplemento())) return false;
        return this.getLogradouro().equals(endereco.getLogradouro());
    }
}
