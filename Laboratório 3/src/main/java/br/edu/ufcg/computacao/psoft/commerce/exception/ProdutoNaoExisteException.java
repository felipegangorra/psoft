package br.edu.ufcg.computacao.psoft.commerce.exception;

public class ProdutoNaoExisteException extends CommerceException {
    public ProdutoNaoExisteException() {
        super("Esse produto não existe!");
    }
}
