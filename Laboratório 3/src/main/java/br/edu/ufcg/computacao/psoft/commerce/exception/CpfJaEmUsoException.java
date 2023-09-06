package br.edu.ufcg.computacao.psoft.commerce.exception;

public class CpfJaEmUsoException extends CommerceException{
    public CpfJaEmUsoException(){
        super("CPF já em uso!");
    }
}
