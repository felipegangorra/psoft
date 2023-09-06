package br.edu.ufcg.computacao.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="logradouros")
public class Logradouro {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true,nullable = false,name = "logradouro_id")
    private Long id;

    @JsonProperty("tipo")
    @Column(nullable = false,name = "logradouro_tipo")
    private String tipo;

    @JsonProperty("nome")
    @Column(nullable = false,name = "logradouro_nome")
    private String nomelog;

    @JsonProperty("bairro")
    @Column(nullable = false,name = "logradouro_bairro")
    private String bairro;

    @JsonProperty("cidade")
    @Column(nullable = false,name = "logradouro_cidade")
    private String cidade;

    @JsonProperty("estado")
    @Column(nullable = false,name = "logradouro_estado")
    private String estado;

    @JsonProperty("pais")
    @Column(nullable = false,name = "logradouro_pais")
    private String pais;

    @JsonProperty("cep")
    @Column(nullable = false,name = "logradouro_cep")
    private String cep;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Logradouro logradouro)) return false;
        if (!this.getTipo().equals(logradouro.getTipo())) return false;
        if (!this.getNomelog().equals(logradouro.getNomelog())) return false;
        if (!this.getBairro().equals(logradouro.getBairro())) return false;
        if (!this.getCidade().equals(logradouro.getCidade())) return false;
        if (!this.getEstado().equals(logradouro.getEstado())) return false;
        if (!this.getPais().equals(logradouro.getPais())) return false;
        return this.getCep().equals(logradouro.getCep());
    }

}
