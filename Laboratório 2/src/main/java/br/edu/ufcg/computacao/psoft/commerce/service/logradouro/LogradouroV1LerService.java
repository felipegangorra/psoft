package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogradouroV1LerService implements LogradouroLerService {

    private LogradouroRepository logradouroRepository;

    @Override
    public Logradouro ler(Long id) {
        Optional<Logradouro> logradouroOptional = logradouroRepository.ler(id);
        return logradouroOptional.orElse(null);
    }

}
