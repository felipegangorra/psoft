package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.service.pessoa.PessoaCriarService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoV1AtualizarService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoV1CriarService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoV1LerService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoV1RemoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoV1RestController {

    @Autowired
    private ProdutoV1CriarService produtoCriarService;
    @Autowired
    private ProdutoV1RemoverService produtoRemoverService;
    @Autowired
    private ProdutoV1LerService produtoLerService;
    @Autowired
    private ProdutoV1AtualizarService produtoAtualizarService;


    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoPostDTO produtoPostDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoCriarService.criar(produtoPostDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lerProduto(@PathVariable Long id) {
        Produto produto = produtoLerService.ler(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable("id") Long id) {
        this.produtoRemoverService.remover(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable("id") Long id, @RequestBody ProdutoPutDTO produto) {
        this.produtoAtualizarService.atualizar(id, produto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
