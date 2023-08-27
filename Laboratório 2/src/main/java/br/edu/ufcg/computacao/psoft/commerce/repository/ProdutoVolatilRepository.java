package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProdutoVolatilRepository implements ProdutoRepository {
    private Map<Long, Produto> produtos;
    private Long nextId;

    public ProdutoVolatilRepository() {
        produtos = new HashMap<>();
        nextId = 1L;
    }

    @Override
    public Produto criar(Produto produto) {
        produto.setId(nextId++);
        produtos.put(produto.getId(), produto);
        return produto;
    }

    @Override
    public Produto ler(Long id) {
        return produtos.get(id);
    }

    @Override
    public List<Produto> lerTodos() {
        return new ArrayList<>(produtos.values());
    }

    @Override
    public Produto atualizar(Long id, Produto produtoAtualizado) {
            produtoAtualizado.setId(id);
            produtos.put(id, produtoAtualizado);
            return produtoAtualizado;
    }

    @Override
    public void remover(Long id) {
        produtos.remove(id);
    }

}
