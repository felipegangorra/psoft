package br.edu.ufcg.computacao.psoft.commerce.service.produto;
import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoV1AtualizarService implements ProdutoAtualizarService{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto atualizar(Long id, ProdutoPutDTO produtoAtualizadoDTO) {
        Produto produtoExistente = produtoRepository.ler(id);

        if (produtoExistente == null) {
            throw new IllegalArgumentException("Produto não encontrado com o ID informado.");
        }

        produtoExistente.setCodigoDeBarras(produtoAtualizadoDTO.getCodigoDeBarras());
        produtoExistente.setValor(produtoAtualizadoDTO.getValor());

        return produtoRepository.atualizar(id, produtoExistente);
    }
}
