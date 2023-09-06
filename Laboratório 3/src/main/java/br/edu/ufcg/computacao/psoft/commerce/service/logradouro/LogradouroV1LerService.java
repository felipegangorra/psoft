package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.exception.LogradouroNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LogradouroV1LerService implements LogradouroLerService{
    @Autowired
    LogradouroRepository logradouroRepository;
    @Override
    public Logradouro ler(Long id) {
        if (!this.logradouroRepository.existsById(id)){
            throw new LogradouroNaoExisteException();
        }
        return this.logradouroRepository.findById(id).get();
    }

    @Override
    public Collection<Logradouro> lerTodos() {
        return this.logradouroRepository.findAll();
    }
}
