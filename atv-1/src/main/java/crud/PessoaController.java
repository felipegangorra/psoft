package crud;

import java.util.ArrayList;

public class PessoaController {

    private PessoaRepository pr;

    public PessoaController() {
        this.pr = new PessoaRepository();
    }

    public void criarPessoa(String nome, String cpf, int idade, String telefone, String profissao, ArrayLis<String> endereco) {
        Pessoa novaPessoa = new Pessoa (nome, cpf, idade, telefone, profissao, endereco);
        //salvar no repositorio
        pr.salvar(novaPessoa);    
    }

    public Pessoa buscarPessoa(String cpf) {
        return pr.buscar(cpf);
    }

    public void removePessoa(String cpf) {
        pr.remove(cpf);
    }
}