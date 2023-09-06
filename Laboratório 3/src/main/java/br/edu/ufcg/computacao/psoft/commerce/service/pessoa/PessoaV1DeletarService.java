package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.exception.PessoaNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaV1DeletarService implements PessoaDeletarService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deletar(Long id) {
        if (!this.pessoaRepository.existsById(id)){
            throw new PessoaNaoExisteException();
        }
        this.pessoaRepository.deleteById(id);
    }
}
