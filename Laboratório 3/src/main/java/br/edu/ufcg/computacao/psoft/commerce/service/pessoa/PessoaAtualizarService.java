package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;

@FunctionalInterface
public interface PessoaAtualizarService {
    public Pessoa atualizar(Long id, PessoaPutDTO pessoaPutDTO);
}
