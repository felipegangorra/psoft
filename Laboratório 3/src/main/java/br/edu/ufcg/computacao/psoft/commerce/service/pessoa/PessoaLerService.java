package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import java.util.Collection;

public interface PessoaLerService {
    public Pessoa ler(Long id);

    public Collection<Pessoa> lerTodos();
}
