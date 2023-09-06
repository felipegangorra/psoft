package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;

@FunctionalInterface
public interface PessoaCriarService {
    public Pessoa criar(PessoaPostDTO pessoaPostDTO);
}
