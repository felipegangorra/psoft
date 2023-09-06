package br.edu.ufcg.computacao.psoft.commerce.service.logradouro;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

import java.util.Collection;

public interface LogradouroLerService {
    public Logradouro ler(Long id);
    public Collection<Logradouro> lerTodos();
}
