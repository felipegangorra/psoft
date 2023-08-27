package br.edu.ufcg.computacao.psoft.commerce.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    private Long id;
    private String nome;
    private String codigoDeBarras;
    private double valor;
    private String fabricante;
}