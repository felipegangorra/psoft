package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

public interface LogradouroAtualizarService {
    public void atualizar(Long id, Logradouro logradouroAtualizado);
}
