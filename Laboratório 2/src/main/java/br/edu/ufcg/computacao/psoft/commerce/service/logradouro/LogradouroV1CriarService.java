package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroV1CriarService implements LogradouroCriarService {

    @Autowired
    private LogradouroRepository logradouroRepository;

    @Override
    public Logradouro criar(LogradouroPostDTO logradouroPostDTO) {

        Logradouro novoLogradouro = new Logradouro();
        novoLogradouro.setTipo(logradouroPostDTO.getTipo());
        novoLogradouro.setNome(logradouroPostDTO.getNome());
        novoLogradouro.setBairro(logradouroPostDTO.getBairro());
        novoLogradouro.setCidade(logradouroPostDTO.getCidade());
        novoLogradouro.setEstado(logradouroPostDTO.getEstado());
        novoLogradouro.setPais(logradouroPostDTO.getPais());
        novoLogradouro.setCep(logradouroPostDTO.getCep());

        return logradouroRepository.criar(novoLogradouro);
    }
}
