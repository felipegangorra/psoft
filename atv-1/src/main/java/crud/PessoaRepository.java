package crud;

import java.util.HashMap;
import java.util.Map;

public class PessoaRepository {

    private HashMap<String, Pessoa> pessoas;

    public PessoaRepository() {
        this.pessoas = new HashMap<>();
    }

    public void salvar(Pessoa pessoa) {
        pessoas.put(pessoa.getCpf(), pessoa);   //padrão
    }

    public Pessoa buscar(String cpf) {
        return pessoas.get(cpf);    //padrão
    }

    public void removePessoa(String cpf) {
        pessoas.remove(cpf);    //padrão
    }

}