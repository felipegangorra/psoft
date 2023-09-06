package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.logradouro.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

public interface LogradouroAtualizarService {
    public Logradouro atualizar(Long id, LogradouroPostDTO logradouroPostPutDTO);
}
