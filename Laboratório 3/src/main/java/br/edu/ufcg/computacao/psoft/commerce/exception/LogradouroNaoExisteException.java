package br.edu.ufcg.computacao.psoft.commerce.exception;

public class LogradouroNaoExisteException extends CommerceException{
    public LogradouroNaoExisteException() {
        super("Esse logradouro não existe!");
    }
}
