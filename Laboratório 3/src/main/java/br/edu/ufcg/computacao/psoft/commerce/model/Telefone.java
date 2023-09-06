package br.edu.ufcg.computacao.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="telefones")
public class Telefone {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true,nullable = false,name = "telefone_id")
    private Long id;

    @JsonProperty("numero_telefone")
    @Column(nullable = false,name="telefone_numero")
    private String numero;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Telefone telefone)) return false;
        return this.getNumero().equals(telefone.getNumero());
    }
}
