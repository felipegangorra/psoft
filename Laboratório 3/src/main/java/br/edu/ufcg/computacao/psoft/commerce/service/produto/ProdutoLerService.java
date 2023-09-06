package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

import java.util.Collection;

public interface ProdutoLerService {
    Produto ler(Long id);
    Collection<Produto> lerTodos();
}
