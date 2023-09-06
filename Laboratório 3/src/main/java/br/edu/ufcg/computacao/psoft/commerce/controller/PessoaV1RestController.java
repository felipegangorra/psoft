package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.service.pessoa.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/v1/pessoas", produces = MediaType.APPLICATION_JSON_VALUE
)
public class PessoaV1RestController {
;
    @Autowired
    private PessoaAtualizarService pessoaAtualizarService;
    @Autowired
    private PessoaCriarService pessoaCriarService;
    @Autowired
    private PessoaLerService pessoaLerService;
    @Autowired
    private PessoaDeletarService pessoaDeletarService;

    @PostMapping
    public ResponseEntity<?> criarPessoa(@Valid @RequestBody PessoaPostDTO pessoaPostDTO) {
        Pessoa pessoaCriada = this.pessoaCriarService.criar(pessoaPostDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pessoaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable("id") Long id, @Valid @RequestBody PessoaPutDTO pessoaPutDTO) {
        Pessoa pessoaAtualizada = this.pessoaAtualizarService.atualizar(id, pessoaPutDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable("id") Long id) {
        pessoaDeletarService.deletar(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lerPessoa(@PathVariable("id") Long id){
        Pessoa pessoa = pessoaLerService.ler(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pessoa);
    }

    @GetMapping()
    public ResponseEntity<?> lerPessoas(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pessoaLerService.lerTodos());
    }
}
