package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.produto.ProdutoPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoAtualizarService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoCriarService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoDeletarService;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.ProdutoLerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/v1/produtos",produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProdutoV1RestController {
    @Autowired
    private ProdutoCriarService produtoCriarService;

    @Autowired
    private ProdutoAtualizarService produtoAtualizarService;

    @Autowired
    private ProdutoDeletarService produtoExcluirService;

    @Autowired
    private ProdutoLerService produtoLerService;

    @PostMapping
    public ResponseEntity<?> criarProduto(@Valid @RequestBody ProdutoPostPutDTO produtoPostPutDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.produtoCriarService.criar(produtoPostPutDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoPostPutDTO produtoPostPutDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.produtoAtualizarService.atualizar(id, produtoPostPutDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable("id") Long id) {
        produtoExcluirService.deletar(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lerProduto(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoLerService.ler(id));
    }

    @GetMapping()
    public ResponseEntity<?> lerProdutos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoLerService.lerTodos());
    }
}
