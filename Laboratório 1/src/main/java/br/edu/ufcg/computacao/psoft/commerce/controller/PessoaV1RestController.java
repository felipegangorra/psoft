package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaV1RestController {

    private Long nextId;    //gerar id único
    private Map<Long, Pessoa> pessoaRepository;

    public PessoaV1RestController() {
        this.nextId = 1L;
        this.pessoaRepository = new HashMap<>();
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa) {
        if(!existeCpf(pessoa.getCpf())) {
            pessoa.setId(nextId++);
            this.pessoaRepository.put(pessoa.getId(), pessoa);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pessoa);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Essa pessoa já existe");
        }
    }

    //verifica se a pessoa já existe no repositorio
    private boolean existeCpf(String cpf) {
        for(Pessoa pessoa: pessoaRepository.values()) {
            if(pessoa.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verPessoa(@PathVariable("id") Long id) {
        if(this.pessoaRepository.containsKey(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.pessoaRepository.get(id));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Essa pessoa não existe");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPessoas() {
        List<Pessoa> pessoas =  new ArrayList<>(this.pessoaRepository.values());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable("id") Long id) {
        if(this.pessoaRepository.containsKey(id)) {
            this.pessoaRepository.remove(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Essa pessoa não existe");
        }
    }

    @PatchMapping("/{id}")        //path
    public ResponseEntity<?> editarEmail(@PathVariable("id") Long id, @RequestBody Pessoa pessoaAtualizada) {
        if(this.pessoaRepository.containsKey(id)) {
            Pessoa pessoaExistente = this.pessoaRepository.get(id);
            pessoaExistente.setEmail(pessoaAtualizada.getEmail());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pessoaExistente);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Essa pessoa não existe");
        }
    }

    @PutMapping         //put
    public ResponseEntity<?> atualizarPessoa(@PathVariable("id") Long id, @RequestBody Pessoa pessoaAtualizada) {
        if(this.pessoaRepository.containsKey(id)) {
            Pessoa pessoaExistente = this.pessoaRepository.get(id);

            pessoaExistente.setId(id);
            pessoaExistente.setEmail(pessoaAtualizada.getEmail());
            pessoaExistente.setTelefone(pessoaAtualizada.getTelefone());
            pessoaExistente.setData_nascimento(pessoaAtualizada.getData_nascimento());
            pessoaExistente.setProfissao(pessoaAtualizada.getProfissao());
            pessoaExistente.setEnderecos(pessoaAtualizada.getEnderecos());

            this.pessoaRepository.put(id, pessoaExistente);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pessoaExistente);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Essa pessoa não existe");
        }
    }

}
