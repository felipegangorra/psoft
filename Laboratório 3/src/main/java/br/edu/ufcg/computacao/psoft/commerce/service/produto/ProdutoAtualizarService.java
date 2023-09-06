package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.dto.produto.ProdutoPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

public interface ProdutoAtualizarService {
    public Produto atualizar(Long id,ProdutoPostPutDTO produtoPostPutDTO);
}
