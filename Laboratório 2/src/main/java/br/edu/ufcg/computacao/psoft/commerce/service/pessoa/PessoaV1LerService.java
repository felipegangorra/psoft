package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PessoaV1LerService implements PessoaLerService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public Pessoa ler(Long id) {
        Pessoa pessoa = pessoaRepository.ler(id);

        if (pessoa != null) {
            return pessoa;
        } else {
            // Lançar uma exceção ou fazer outra ação, caso o registro não exista
            throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
        }
    }

}