package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

@FunctionalInterface
public interface ProdutoAtualizarService {
    public Produto atualizar(Long id, ProdutoPutDTO produtoPutDTO);
}
