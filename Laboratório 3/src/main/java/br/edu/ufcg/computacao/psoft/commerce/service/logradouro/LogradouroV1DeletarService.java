package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.exception.LogradouroNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroV1DeletarService implements LogradouroDeletarService {
    @Autowired
    LogradouroRepository logradouroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deletar(Long id) {
        if (!this.logradouroRepository.existsById(id)){
            throw new LogradouroNaoExisteException();
        }
        this.logradouroRepository.deleteById(id);
    }
}
