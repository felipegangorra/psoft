package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.exception.PessoaNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PessoaV1LerService implements PessoaLerService{
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa ler(Long id) {
        if (!this.pessoaRepository.existsById(id)){
            throw new PessoaNaoExisteException();
        }
        return this.pessoaRepository.findById(id).get();

    }

    @Override
    public Collection<Pessoa> lerTodos() {
        return pessoaRepository.findAll();
    }
}
