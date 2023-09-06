package br.edu.ufcg.computacao.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoas")
public class Pessoa {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique=true,nullable=false,name = "pk_id_pessoa")
    private Long id;

    @JsonProperty("cpf")
    @Column(nullable = false, name = "cpf_pessoa")
    private String cpf;

    @JsonProperty("nome")
    @Column(nullable = false,name="nome_pessoa")
    private String nome;

    @JsonProperty("email")
    @Column(nullable = false,name = "email_pessoa")
    private String email;

    @JsonProperty("telefones")
    @Column(nullable = false,name = "telefones_pessoa")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    @JsonProperty("nascimento")
    @Column(nullable = false,name = "nascimento_pessoas")
    private String nascimento;

    @JsonProperty("enderecos")
    @Column(nullable = false,name = "enderecos_pessoas")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @JsonProperty("profissao")
    @Column(nullable = false,name = "profissao_pessoas")
    private String profissao;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Pessoa pessoa)) return false;
        if (!this.getCpf().equals(pessoa.getCpf())) return false;
        if (!this.getNome().equals(pessoa.getNome())) return false;
        if (!this.getNascimento().equals(pessoa.getNascimento())) return false;
        return this.getEmail().equals(pessoa.getEmail());
    }
}
