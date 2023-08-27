package br.edu.ufcg.computacao.psoft.commerce.service.produto;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoV1LerService implements ProdutoLerService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public Produto ler(Long id) {
        Produto produto = produtoRepository.ler(id);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        return produto;
    }

}