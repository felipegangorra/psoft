package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    public Produto criar(Produto produto);
    public void remover(Long id);
    public Produto ler(Long id);
    public List<Produto> lerTodos();
    public Produto atualizar(Long id, Produto produtoAtualizada);
}
