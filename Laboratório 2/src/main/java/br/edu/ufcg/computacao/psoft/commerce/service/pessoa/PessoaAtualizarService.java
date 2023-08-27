package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;

@FunctionalInterface
public interface PessoaAtualizarService {
    public void atualizar(Long id, PessoaPutDTO pessoaPutDTO);
}
