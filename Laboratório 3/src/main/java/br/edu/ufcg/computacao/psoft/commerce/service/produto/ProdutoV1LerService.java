package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProdutoV1LerService implements ProdutoLerService{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto ler(Long id) {
        return this.produtoRepository.findById(id).get();
    }

    @Override
    public Collection<Produto> lerTodos() {
        return this.produtoRepository.findAll();
    }
}
