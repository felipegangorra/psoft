package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class LogradouroVolatilRepository implements LogradouroRepository {

    private Map<Long, Logradouro> logradouros = new HashMap<>();
    private long idCounter = 1;

    public Logradouro criar(Logradouro logradouro) {
        logradouro.setId(idCounter);
        logradouros.put(idCounter, logradouro);
        idCounter++;
        return logradouro;
    }

    @Override
    public Optional<Logradouro> ler(Long id) {
        return Optional.ofNullable(logradouros.get(id));
    }

    public void remover(Long id) {
        logradouros.remove(id);
    }

    public void atualizar(Long id, Logradouro logradouroAtualizado) {
        if (logradouros.containsKey(id)) {
            logradouroAtualizado.setId(id);
            logradouros.put(id, logradouroAtualizado);
        }
    }
}
