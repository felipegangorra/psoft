package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PessoaV1RemoverService implements PessoaRemoverService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public void remover(Long id) {
        Pessoa pessoa = pessoaRepository.ler(id);
        if (pessoa == null) {
            throw new RuntimeException("Pessoa com ID " + id + " não encontrada.");
        }
        pessoaRepository.remover(id);
    }

}