package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.dto.produto.ProdutoPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import jakarta.validation.Valid;

@FunctionalInterface
public interface ProdutoCriarService {
    public Produto criar(@Valid ProdutoPostPutDTO produtoPostPutDTO);
}
