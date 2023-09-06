package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.logradouro.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.LogradouroNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroV1AtualizarService implements LogradouroAtualizarService{
    @Autowired
    LogradouroRepository logradouroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Logradouro atualizar(Long id, LogradouroPostDTO logradouroPutDTO) {
        if (!this.logradouroRepository.existsById(id)){
            throw new LogradouroNaoExisteException();
        }
        return this.logradouroRepository.save(
                modelMapper.map(logradouroPutDTO,Logradouro.class)
        );
    }

}
