package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.logradouro.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroAtualizarService;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroCriarService;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroDeletarService;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroLerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/v1/logradouros", produces = MediaType.APPLICATION_JSON_VALUE
)
public class LogradouroV1RestController {

    @Autowired
    private LogradouroAtualizarService logradouroAtualizarService;
    @Autowired
    private LogradouroLerService logradouroLerService;
    @Autowired
    private LogradouroCriarService logradouroCriarService;
    @Autowired
    private LogradouroDeletarService logradouroDeletarService;

    @PostMapping
    public ResponseEntity<?> criarLogradouro(@Valid @RequestBody LogradouroPostDTO logradouroPostDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.logradouroCriarService.criar(logradouroPostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLogradouro(@PathVariable("id") Long id, @Valid @RequestBody LogradouroPostDTO logradouroPostDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.logradouroAtualizarService.atualizar(id, logradouroPostDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLogradouro(@PathVariable("id") Long id) {
        logradouroDeletarService.deletar(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lerLogradouro(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logradouroLerService.ler(id));
    }

    @GetMapping()
    public ResponseEntity<?> lerlograoduros(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logradouroLerService.lerTodos());
    }
}