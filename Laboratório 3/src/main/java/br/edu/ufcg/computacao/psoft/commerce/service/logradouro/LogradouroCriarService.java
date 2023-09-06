package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.logradouro.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

@FunctionalInterface
public interface LogradouroCriarService {
    public Logradouro criar(LogradouroPostDTO logradouroPostDTO);
}
