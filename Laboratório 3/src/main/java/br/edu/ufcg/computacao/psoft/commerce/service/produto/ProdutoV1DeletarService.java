package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.exception.ProdutoNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoV1DeletarService implements ProdutoDeletarService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deletar(Long id) {
        if (!this.produtoRepository.existsById(id)){
            throw new ProdutoNaoExisteException();
        }
        this.produtoRepository.deleteById(id);
    }
}
