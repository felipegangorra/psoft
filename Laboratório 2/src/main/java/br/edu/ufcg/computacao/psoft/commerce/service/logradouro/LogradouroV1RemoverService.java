package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroV1RemoverService implements LogradouroRemoverService {

    @Autowired
    private LogradouroRepository logradouroRepository;

    @Override
    public void remover(Long id) {
        logradouroRepository.remover(id);
    }
}
