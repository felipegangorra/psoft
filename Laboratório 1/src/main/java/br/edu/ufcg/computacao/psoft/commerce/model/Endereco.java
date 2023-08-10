package br.edu.ufcg.computacao.psoft.commerce.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Endereco {
    @Id
    private Long id;
    private Long id_Pessoa;     //cliente vai fazer a associação passando esse id
    private Long numero;
    private String tipo_logradouro;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
}
