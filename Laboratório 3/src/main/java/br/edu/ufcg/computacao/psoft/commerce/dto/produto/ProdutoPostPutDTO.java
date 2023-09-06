package br.edu.ufcg.computacao.psoft.commerce.dto.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.EAN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostPutDTO {
    private Long id;

    @JsonProperty("nome")
    @NotBlank(message = "Nome não pode ser vazio!")
    private String nome;

    @JsonProperty("Codigo_de_barras")
    @NotBlank(message = "Codigo de barras não pode ser vazio!")
    @EAN(message = "Codigo de barras invalido!")
    private String codbarras;

    @JsonProperty("valor")
    @NotNull(message = "Não existe produto gratis!")
    @Positive
    private Float valor;

    @JsonProperty("nome_do_fabricante")
    @NotBlank(message = "Nome do fabricante não pode ser vazio!")
    private String nomefab;
}
