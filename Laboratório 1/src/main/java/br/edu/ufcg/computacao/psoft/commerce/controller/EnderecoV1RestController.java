package br.edu.ufcg.computacao.psoft.commerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ufcg.computacao.psoft.commerce.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping(
        value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE
)
public class EnderecoV1RestController {

    private Long nextId;
    private Map<Long, Endereco> enderecoRepository;
    public EnderecoV1RestController(){
        this.enderecoRepository = new HashMap<>();
        this.nextId = 1L;
    }

    //não precisa de verificação já que pode ter mais de um endereço igual
    @PostMapping
    public ResponseEntity<?> criarEndereco(@RequestBody Endereco endereco){
        endereco.setId(nextId++);
        enderecoRepository.put(endereco.getId(), endereco);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(endereco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verEndereco(@PathVariable("id") Long id) {
        if(enderecoRepository.containsKey(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(enderecoRepository.get(id));
        }
        else {
            return ResponseEntity
                    .status((HttpStatus.NOT_FOUND))
                    .body("Endereço não existe");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarEnderecos(){
        List<Endereco> enderecos = new ArrayList<>(this.enderecoRepository.values());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enderecos);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> editarEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        if(this.enderecoRepository.containsKey(id)) {
            Endereco enderecoExistente = enderecoRepository.get(id);

            enderecoExistente.setNumero(enderecoAtualizado.getNumero());
            enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(enderecoExistente);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereço não existe");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        if(this.enderecoRepository.containsKey(id)) {
            Endereco enderecoExistente = this.enderecoRepository.get(id);

            enderecoExistente.setId(id);
            enderecoExistente.setId_Pessoa(enderecoAtualizado.getId_Pessoa());
            enderecoExistente.setCep(enderecoAtualizado.getCep());
            enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
            enderecoExistente.setTipo_logradouro(enderecoAtualizado.getTipo_logradouro());
            enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
            enderecoExistente.setNumero(enderecoAtualizado.getNumero());
            enderecoExistente.setCidade(enderecoAtualizado.getCidade());
            enderecoExistente.setBairro(enderecoAtualizado.getBairro());
            enderecoExistente.setEstado(enderecoAtualizado.getEstado());
            enderecoExistente.setPais(enderecoAtualizado.getPais());

            this.enderecoRepository.put(id, enderecoExistente);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(enderecoExistente);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereço não existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEndereco(@PathVariable Long id){
        if(this.enderecoRepository.containsKey(id)){
            enderecoRepository.remove(id);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereco não existe");
        }
    }

}