package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

public interface LogradouroCriarService {
    public Logradouro criar(LogradouroPostDTO logradouroPostDTO);
}
