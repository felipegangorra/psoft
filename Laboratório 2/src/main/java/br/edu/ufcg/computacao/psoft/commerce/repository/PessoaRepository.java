package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;

public interface PessoaRepository {
    public Pessoa criar(Pessoa pessoa);
    public void remover(Long id);
    public Pessoa ler(Long id);
    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada);
}
