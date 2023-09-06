package br.edu.ufcg.computacao.psoft.commerce.exception;

public class PessoaNaoExisteException extends CommerceException {
    public PessoaNaoExisteException() {
        super("Essa pessoa não existe!");
    }

}
