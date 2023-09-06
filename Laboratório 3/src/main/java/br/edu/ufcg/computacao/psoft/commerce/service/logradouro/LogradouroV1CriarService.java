package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.logradouro.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroV1CriarService implements LogradouroCriarService{
    @Autowired
    private LogradouroRepository logradouroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Logradouro criar(LogradouroPostDTO logradouroPostPutDTO) {
        return this.logradouroRepository.save(
                modelMapper.map(logradouroPostPutDTO, Logradouro.class)
        );
    }
}
