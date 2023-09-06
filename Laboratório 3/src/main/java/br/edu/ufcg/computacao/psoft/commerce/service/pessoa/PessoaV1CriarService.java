package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.CpfJaEmUsoException;
import br.edu.ufcg.computacao.psoft.commerce.exception.EmailJaEmUsoException;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaV1CriarService implements PessoaCriarService{
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Pessoa criar(PessoaPostDTO pessoaPostDTO) {
        if (this.pessoaRepository.findByEmail(pessoaPostDTO.getEmail())!=null){
            throw new EmailJaEmUsoException();
        }
        if (this.pessoaRepository.findByCpf(pessoaPostDTO.getCpf())!=null){
            throw new CpfJaEmUsoException();
        }
        return this.pessoaRepository.save(
                modelMapper.map(pessoaPostDTO, Pessoa.class)
        );
    }
}
