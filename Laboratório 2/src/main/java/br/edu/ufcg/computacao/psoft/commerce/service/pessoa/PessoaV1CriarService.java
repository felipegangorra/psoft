package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;
import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaV1CriarService implements PessoaCriarService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public Pessoa criar(PessoaPostDTO pessoaPostDTO) {

        validarPessoa(pessoaPostDTO);

        return pessoaRepository.criar(
                Pessoa.builder()
                        .nome(pessoaPostDTO.getNome())
                        .cpf(pessoaPostDTO.getCpf())
                        .telefone(pessoaPostDTO.getTelefone())
                        .email(pessoaPostDTO.getEmail())
                        .data_nascimento(pessoaPostDTO.getData_nascimento())
                        .profissao((pessoaPostDTO.getProfissao()))
                        .enderecos(pessoaPostDTO.getEnderecos())
                        .build()
                );
    }

    private void validarPessoa(PessoaPostDTO pessoaPostDTO) {
        if (pessoaPostDTO.getNome() == null || pessoaPostDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da pessoa é obrigatório.");
        }
        if (pessoaPostDTO.getCpf() == null || pessoaPostDTO.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF da pessoa é obrigatório.");
        }
    }

}