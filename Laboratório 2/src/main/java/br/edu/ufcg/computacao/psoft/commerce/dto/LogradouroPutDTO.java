package br.edu.ufcg.computacao.psoft.commerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogradouroPutDTO {
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("nome")
    private String nome;
}
