package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PessoaVolatilRepository implements PessoaRepository {
    private Map<Long, Pessoa> pessoas;
    private Long nextId;

    public PessoaVolatilRepository() {
        pessoas = new HashMap<>();
        nextId = 0L;
    }

    @Override
    public Pessoa criar(Pessoa pessoa) {
        pessoa.setId(nextId++);
        pessoas.put(pessoa.getId(), pessoa);
        return pessoa;
    }

    @Override
    public void remover(Long id) {
        pessoas.remove(id);
    }

    @Override
    public Pessoa ler(Long id) {
        return pessoas.get(id);
    }

    @Override
    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        pessoaAtualizada.setId(id);
        pessoas.put(id, pessoaAtualizada);
        return pessoaAtualizada;
    }

}
