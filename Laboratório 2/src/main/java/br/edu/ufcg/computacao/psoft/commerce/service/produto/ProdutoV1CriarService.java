package br.edu.ufcg.computacao.psoft.commerce.service.produto;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoV1CriarService implements ProdutoCriarService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto criar(ProdutoPostDTO produtoPostDTO) {
        validarProdutoDTO(produtoPostDTO);

        return produtoRepository.criar(
                Produto.builder()
                        .nome(produtoPostDTO.getNome())
                        .codigoDeBarras(produtoPostDTO.getCodigoDeBarras())
                        .valor(produtoPostDTO.getValor())
                        .fabricante(produtoPostDTO.getFabricante())
                        .build()
        );
    }

    private void validarProdutoDTO(ProdutoPostDTO produtoPostDTO) {
        if (produtoPostDTO.getNome() == null || produtoPostDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (produtoPostDTO.getFabricante() == null || produtoPostDTO.getFabricante().isEmpty()) {
            throw new IllegalArgumentException("O nome do fabricante do produto é obrigatório.");
        }
    }

}
