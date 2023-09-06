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
@Entity(name="produtos")
public class Produto {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true,nullable = false,name = "produto_id")
    private Long id;

    @JsonProperty("nome")
    @Column(nullable = false,name = "produto_nome")
    private String nome;

    @JsonProperty("codigo_de_barras")
    @Column(nullable = false,name = "produto_codigo_barras")
    private String codbarras;

    @JsonProperty("nome_do_fabricante")
    @Column(nullable = false,name = "produto_fabricante")
    private String nomefab;

    @JsonProperty("valor")
    @Column(nullable = false,name = "produto_valor")
    private Float valor;

}
