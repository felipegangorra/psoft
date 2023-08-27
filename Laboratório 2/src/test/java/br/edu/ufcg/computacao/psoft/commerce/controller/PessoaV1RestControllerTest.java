package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DisplayName("Testes da Entidade Pessoa")
@AutoConfigureMockMvc
class PessoaV1RestControllerTest {

    final String URL_TEMPLATE = "/v1/pessoas";

    @Autowired
    MockMvc driver;

    @Autowired
    PessoaRepository pessoaRepository;

    Pessoa pessoa;
    PessoaPostDTO pessoaPostDTO;
    PessoaPutDTO pessoaPutDTO;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        //cliente = clienteRepository.save(Cliente.builder()
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Felipe")
                .cpf("11111111111")
                .telefone("3333333")
                .email("email@gmail.com")
                .data_nascimento("121212")
                .profissao("sofredor")
                .enderecos(null)
                .build();

        pessoa = pessoaRepository.criar(Pessoa.builder()
                .nome("Felipe")
                .cpf("11111111111")
                .telefone("3333333")
                .email("email@gmail.com")
                .data_nascimento("121212")
                .profissao("sofredor")
                .enderecos(null)
                .build());

        pessoaPutDTO = PessoaPutDTO.builder()
                .id(1L)
                .telefone("3333333")
                .email("email@gmail.com")
                .data_nascimento("121212")
                .profissao("sofredor")
                .enderecos(null)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    //classe especifica de teste
    @Nested
    @DisplayName("Criação de Pessoas")
    class ValidacaoDeEntradas {

        //teste dentro dessa classe especifica
        @Test
        @DisplayName("Quando criar pessoa com dados válidos")
        void quandoCriarPessoaValida() throws Exception {
            //arrange

            //act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.post("/v1/pessoas")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            Pessoa pessoa1 = objectMapper.readValue(responseJsonString, Pessoa.class);

            Pessoa pessoaSalvo = pessoaRepository.ler(pessoa1.getId());


            assertNotNull(pessoaSalvo);
            assertEquals(pessoaPostDTO.getNome(), pessoaSalvo.getNome());
            assertEquals(pessoaPostDTO.getCpf(), pessoaSalvo.getCpf());
            assertEquals(pessoaPostDTO.getData_nascimento(), pessoaSalvo.getData_nascimento());
        }

        @Test
        @DisplayName("Quando criar pessoa com dados invalidos")
        void quandoCriarPessoaInvalida() throws Exception {
            // Implementar os testes aqui
        }
    }

    @Nested
    @DisplayName("Leitura de pessoa")
    class LeituraDePessoas {
        @Test
        @DisplayName("buscando pessoa valida pelo id")
        void quandoBuscamosPorUmPessoaSalva() throws Exception {
            // Arrange
            // Act
            String resultadoStr = driver.perform(MockMvcRequestBuilders.get(URL_TEMPLATE + "/{id}", pessoaPostDTO.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                    .andExpect(status().isOk())    //codigo 201
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            Pessoa pessoaResultado = objectMapper.readValue(resultadoStr, Pessoa.class);

            // Assert
            assertNotNull(pessoaResultado.getId());
            assertNotNull(pessoaResultado.getNome());
            assertNotNull(pessoaResultado.getCpf());

        }
    }

    @Nested
    @DisplayName("Remoção de pessoa")
    class RemocaoDePessoas {
        @Test
        @DisplayName("removendo pessoa valida pelo id")
        void quandoRemovemosUmaPessoaSalva() throws Exception {
            // Arrange
            // Act
            String resultadoStr = driver.perform(MockMvcRequestBuilders.delete(URL_TEMPLATE + "/{id}", pessoaPostDTO.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                    .andExpect(status().isNoContent())    //codigo 201
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(resultadoStr.isBlank());

        }
    }

    @Nested
    @DisplayName("Atualização de pessoa")
    class atualizaçãoDePessoas {
        @Test
        @DisplayName("atualizando pessoa valida")
        void quandoAtualizaUmaPessoaSalva() throws Exception {
            // Arrange
            // Act
            String resultadoStr = driver.perform(MockMvcRequestBuilders.put(URL_TEMPLATE + "/{id}", pessoaPutDTO.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(pessoaPutDTO)))
                    .andExpect(status().isOk())    //codigo 201
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            Pessoa pessoaResultado = objectMapper.readValue(resultadoStr, Pessoa.class);

            // Assert
            assertNotNull(pessoaResultado);
            assertEquals(pessoaPutDTO.getId(), pessoaResultado.getId());
            assertEquals(pessoaPutDTO.getEmail(), pessoaResultado.getEmail());
            assertEquals(pessoaPutDTO.getTelefone(), pessoaResultado.getTelefone());
            assertEquals(pessoaPutDTO.getData_nascimento(), pessoaResultado.getData_nascimento());
            assertEquals(pessoaPutDTO.getProfissao(), pessoaResultado.getProfissao());
            assertEquals(pessoaPutDTO.getEnderecos(), pessoaResultado.getEnderecos());


        }
    }


}
