package br.edu.ufcg.computacao.psoft.commerce.exception;

public class EmailJaEmUsoException extends CommerceException{
    public EmailJaEmUsoException() {
        super("Esse email já esta em uso!");
    }
}
