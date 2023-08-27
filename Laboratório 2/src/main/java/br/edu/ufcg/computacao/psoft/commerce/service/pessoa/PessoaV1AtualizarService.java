package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;


import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PessoaV1AtualizarService implements PessoaAtualizarService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public void atualizar(Long id, PessoaPutDTO pessoaAtualizada) {
        Pessoa pessoaExistente = this.pessoaRepository.ler(id);

        pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        pessoaExistente.setTelefone(pessoaAtualizada.getTelefone());
        pessoaExistente.setData_nascimento(pessoaAtualizada.getData_nascimento());
        pessoaExistente.setProfissao(pessoaAtualizada.getProfissao());
        pessoaExistente.setEnderecos(pessoaAtualizada.getEnderecos());

        this.pessoaRepository.atualizar(id, pessoaExistente);
    }

}