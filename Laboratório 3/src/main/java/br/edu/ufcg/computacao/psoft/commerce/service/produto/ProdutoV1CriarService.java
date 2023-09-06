package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import br.edu.ufcg.computacao.psoft.commerce.dto.produto.ProdutoPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.CodigoDeBarrasInvalidoException;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoV1CriarService implements ProdutoCriarService{

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Produto criar(ProdutoPostPutDTO produtoPostPutDTO) {
        if (!CodigoDeBarrasService.verificarPadrao(produtoPostPutDTO.getCodbarras())){
            throw new CodigoDeBarrasInvalidoException();
        }
        return this.produtoRepository.save(
                modelMapper.map(produtoPostPutDTO,Produto.class)
        );
    }
}
