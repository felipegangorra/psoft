package br.edu.ufcg.computacao.psoft.commerce.repository;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {}
