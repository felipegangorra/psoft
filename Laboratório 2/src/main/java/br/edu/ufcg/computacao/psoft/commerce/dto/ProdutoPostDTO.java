package br.edu.ufcg.computacao.psoft.commerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPostDTO {
    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("codigoDeBarras")
    private String codigoDeBarras;
    @JsonProperty("valor")
    private double valor;
    @JsonProperty("fabricante")
    private String fabricante;

}
