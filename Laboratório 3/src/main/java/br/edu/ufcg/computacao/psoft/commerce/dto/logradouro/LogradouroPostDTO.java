package br.edu.ufcg.computacao.psoft.commerce.dto.logradouro;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogradouroPostDTO {
    @JsonProperty("tipo")
    @NotBlank(message = "Tipo não pode ser vazio!")
    private String tipo;

    @JsonProperty("nome")
    @NotBlank(message = "Nome não pode ser vazio!")
    private String nomelog;

    @JsonProperty("bairro")
    @NotBlank(message = "Bairro não pode ser vazio!")
    private String bairro;

    @JsonProperty("cidade")
    @NotBlank(message = "Cidade não pode ser vazio!")
    private String cidade;

    @JsonProperty("estado")
    @NotBlank(message = "Estado não pode ser vazio!")
    private String estado;

    @JsonProperty("pais")
    @NotBlank(message = "Pais não pode ser vazio!")
    private String pais;

    @JsonProperty("cep")
    @NotBlank(message = "CPF não pode ser vazio!")
    private String cep;
}
