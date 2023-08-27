package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoRemoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoV1RemoverService implements ProdutoRemoverService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public void remover(Long id) {
        produtoRepository.remover(id);
    }

}