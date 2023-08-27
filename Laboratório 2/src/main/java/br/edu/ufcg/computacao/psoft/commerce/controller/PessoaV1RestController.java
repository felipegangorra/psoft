package br.edu.ufcg.computacao.psoft.commerce.controller;


import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.service.pessoa.PessoaAtualizarService;
import br.edu.ufcg.computacao.psoft.commerce.service.pessoa.PessoaCriarService;
import br.edu.ufcg.computacao.psoft.commerce.service.pessoa.PessoaLerService;
import br.edu.ufcg.computacao.psoft.commerce.service.pessoa.PessoaRemoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaV1RestController {

    @Autowired
    private PessoaCriarService pessoaCriarService;
    @Autowired
    private PessoaLerService pessoaLerService;
    @Autowired
    private PessoaRemoverService pessoaRemoverService;
    @Autowired
    private PessoaAtualizarService pessoaAtualizarService;

    @PostMapping
    public ResponseEntity<?> criarPessoa(@RequestBody PessoaPostDTO pessoaPostDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pessoaCriarService.criar(pessoaPostDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lerPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaLerService.ler(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable("id") Long id) {
        this.pessoaRemoverService.remover(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable("id") Long id, @RequestBody PessoaPutDTO pessoa) {
        this.pessoaAtualizarService.atualizar(id, pessoa);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
