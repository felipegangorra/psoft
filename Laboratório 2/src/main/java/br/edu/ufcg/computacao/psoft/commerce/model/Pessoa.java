package br.edu.ufcg.computacao.psoft.commerce.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    @Id
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String data_nascimento;
    private String profissao;
    private List<Long> enderecos;
}