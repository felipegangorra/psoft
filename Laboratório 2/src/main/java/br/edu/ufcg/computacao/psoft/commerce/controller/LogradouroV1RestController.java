package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroV1AtualizarService;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroV1CriarService;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroV1LerService;
import br.edu.ufcg.computacao.psoft.commerce.service.logradouro.LogradouroV1RemoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/logradouros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogradouroV1RestController {

    @Autowired
    private LogradouroV1CriarService logradouroCriarService;
    @Autowired
    private LogradouroV1LerService logradouroLerService;
    @Autowired
    private LogradouroV1RemoverService logradouroRemoverService;
    @Autowired
    private LogradouroV1AtualizarService logradouroAtualizarService;

    @PostMapping
    public ResponseEntity<Logradouro> criarLogradouro(@RequestBody LogradouroPostDTO logradouroPostDTO) {
        Logradouro logradouro = logradouroCriarService.criar(logradouroPostDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(logradouro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> lerLogradouro(@PathVariable Long id) {
        Logradouro logradouro = logradouroLerService.ler(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logradouro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLogradouro(@PathVariable Long id) {
        logradouroRemoverService.remover(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLogradouro(@PathVariable Long id, @RequestBody Logradouro logradouro) {
        logradouroAtualizarService.atualizar(id, logradouro);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
