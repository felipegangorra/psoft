package br.edu.ufcg.computacao.psoft.commerce.dto.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.model.Endereco;
import br.edu.ufcg.computacao.psoft.commerce.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaPutDTO {
    @JsonProperty("email")
    @NotBlank(message = "Email não pode ser vazio!")
    private String email;

    @JsonProperty("telefones")
    private List<Telefone> telefones;

    @JsonProperty("nascimento")
    private String nascimento;

    @JsonProperty("enderecos")
    private List<Endereco> enderecos;

    @JsonProperty("profissao")
    @NotBlank(message = "Profissao não pode ser vazia!")
    private String profissao;
}
