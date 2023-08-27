package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroVolatilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroV1AtualizarService implements LogradouroAtualizarService {

    private LogradouroRepository logradouroRepository;

    @Override
    public void atualizar(Long id, Logradouro logradouroAtualizado) {
        logradouroRepository.atualizar(id, logradouroAtualizado);
    }
}
