package br.edu.ufcg.computacao.psoft.commerce.service.pessoa;

import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.PessoaNaoExisteException;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaV1AtualizarService implements PessoaAtualizarService{
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Pessoa atualizar(Long id, PessoaPutDTO pessoaPutDTO) {
        if (!this.pessoaRepository.existsById(id)){
            throw new PessoaNaoExisteException();
        }

        Pessoa pessoaAtualizada = this.pessoaRepository.findById(id).get();

        pessoaAtualizada.setEmail(pessoaPutDTO.getEmail());
        pessoaAtualizada.setNascimento(pessoaPutDTO.getNascimento());
        pessoaAtualizada.setProfissao(pessoaPutDTO.getProfissao());
        pessoaAtualizada.setTelefones(pessoaPutDTO.getTelefones());
        return this.pessoaRepository.save(
                pessoaAtualizada
        );
    }
}
