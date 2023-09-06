package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Pessoa findByCpf(String cpf);
    Pessoa findByEmail(String email);
}
