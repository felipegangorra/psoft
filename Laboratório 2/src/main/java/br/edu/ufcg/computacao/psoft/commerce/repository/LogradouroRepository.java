package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

import java.util.Optional;

public interface LogradouroRepository {
    public void remover(Long id);
    public Logradouro criar(Logradouro logradouro);
    public Optional<Logradouro> ler(Long id);
    public void atualizar(Long id, Logradouro logradouroAtualizado);
}
