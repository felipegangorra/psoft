package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

public interface ProdutoCriarService {
    public Produto criar(ProdutoPostDTO produtoPostDTO);
}
