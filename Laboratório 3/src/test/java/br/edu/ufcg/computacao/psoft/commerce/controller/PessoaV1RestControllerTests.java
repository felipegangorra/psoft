package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.dto.pessoa.PessoaPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.CustomErrorType;
import br.edu.ufcg.computacao.psoft.commerce.model.Endereco;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.model.Telefone;
import br.edu.ufcg.computacao.psoft.commerce.repository.PessoaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DisplayName("teste da entidade pessoa")
@AutoConfigureMockMvc
class PessoaV1RestControllerTests {

    final String URL = "/v1/pessoas";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MockMvc driver;

    @Autowired
    PessoaRepository pessoaRepository;

    PessoaPutDTO pessoaPutDTO;

    PessoaPostDTO pessoaPostDTO;

    @BeforeEach
    void setUp() {
        this.pessoaRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {}

    @Test
    @DisplayName("criando pessoa com dados validos")
    void pessoaPostTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                        )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                                            .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                                    .andExpect(status().isCreated())
                                    .andDo(print())
                                    .andReturn().getResponse().getContentAsString();
        Pessoa pessoaResultado = objectMapper.readValue(resultado, Pessoa.class);
        //assert
        assertAll(
                () -> assertNotNull(pessoaResultado.getId()),
                () -> assertTrue(pessoaResultado.getId() > 0),
                () -> assertNotNull(pessoaResultado.getNome()),
                () -> assertNotNull(pessoaResultado.getCpf()),
                () -> assertNotNull(pessoaResultado.getEmail()),
                () -> assertNotNull(pessoaResultado.getProfissao()),
                () -> assertEquals(pessoaResultado.getNome(),pessoaPostDTO.getNome()),
                () -> assertEquals(pessoaResultado.getCpf(),pessoaPostDTO.getCpf()),
                () -> assertEquals(pessoaResultado.getTelefones(),pessoaPostDTO.getTelefones()),
                () -> assertEquals(pessoaResultado.getProfissao(),pessoaPostDTO.getProfissao()),
                () -> assertEquals(pessoaResultado.getNascimento(),pessoaPostDTO.getNascimento()),
                () -> assertEquals(pessoaResultado.getEmail(),pessoaPostDTO.getEmail())
            );

    }

    @Test
    @DisplayName("ciando uma pessoa sem nome, dando erro")
    void pessoaSemNomePostTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação!",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O nome não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("criando uma pessoa sem email, dando erro")
    void pessoaSemEmailPostTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("naturo")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação!",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O email não pode ser vazio|"))
                )
        );

    }

    @Test
    @DisplayName("criando uma pessoa sem cpf, dando erro")
    void pessoaSemcpfPostTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("naruto")
                .email("naruto@365.gmail.com")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação!",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O cpf não pode ser vazio!"))
                )
        );

    }

    @Test
    @DisplayName("criando uma pessoa desempregada, dando erro")
    void pessoaSemTrabalhoPostTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação!",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("A profissao não pode ser vazia!"))
                )
        );

    }

    @Test
    @DisplayName("criando pessoa com CPF repetido, retorna expection")
    void pessoaPostCpfRepetidoTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();

        Pessoa pessoaInicial = pessoaRepository.save(modelMapper.map(pessoaPostDTO, Pessoa.class));
        pessoaPostDTO.setEmail("email@email.com");

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("O cpf já esta em uso!", customErrorType.getMessage())
        );

    }

    @Test
    @DisplayName("atualizar pessoa com dados válidos")
    void pessoaPutTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();

        Pessoa pessoaAntiga = pessoaRepository.save(modelMapper.map(pessoaPostDTO, Pessoa.class));
        pessoaPutDTO = PessoaPutDTO.builder()
                .email("outroemail@email.com")
                .nascimento("09/09/2999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("00000000000").build(),
                                        Telefone.builder().numero("10101010101").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("00000000")
                                                                .nomelog("narnia")
                                                                .tipo("reino")
                                                                .pais("narnia")
                                                                .estado("narnia")
                                                                .cidade("narnia")
                                                                .bairro("narnia")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco.builder()
                                                .numero(120)
                                                .complemento("el dourado")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("ouro")
                                                                .nomelog("ouro")
                                                                .tipo("prata")
                                                                .pais("bronze")
                                                                .estado("diamante")
                                                                .cidade("el dourado")
                                                                .bairro("amigos")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Ladrão")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.put(URL + "/" + pessoaAntiga.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPutDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Pessoa pessoaResultado = objectMapper.readValue(resultado, Pessoa.class);
        //assert
        assertAll(
                () -> assertNotNull(pessoaResultado.getId()),
                () -> assertTrue(pessoaResultado.getId() > 0),
                () -> assertNotNull(pessoaResultado.getNome()),
                () -> assertNotNull(pessoaResultado.getCpf()),
                () -> assertNotNull(pessoaResultado.getEmail()),
                () -> assertNotNull(pessoaResultado.getProfissao()),
                () -> assertEquals(pessoaResultado.getProfissao(),pessoaPutDTO.getProfissao()),
                () -> assertEquals(pessoaResultado.getNascimento(),pessoaPutDTO.getNascimento()),
                () -> assertEquals(pessoaResultado.getTelefones(),pessoaPutDTO.getTelefones()),
                () -> assertEquals(pessoaResultado.getEmail(),pessoaPutDTO.getEmail())
        );

    }

    @Test
    @DisplayName("deletando uma pessoa")
    void pessoaDeleteTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();

        Pessoa pessoaInicial = pessoaRepository.save(modelMapper.map(pessoaPostDTO, Pessoa.class));
        Long pessoasSize = pessoaRepository.count();

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.delete(URL + "/" + pessoaInicial.getId()))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        //assert
        assertAll(
                () -> assertEquals(1L, pessoasSize - pessoaRepository.count())
        );
    }

    @Test
    @DisplayName("deletando uma pessoa não existente, dando erro")
    void pessoaInexistenteDeleteTest() throws Exception {
        //arrange
        Pessoa pessoaInicial = Pessoa.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.delete(URL + "/" + pessoaInicial.getId()))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado,CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Impossivel, essa pessoa não existe!",customErrorType.getMessage())
        );
    }

    @Test
    @DisplayName("lendo pessoa")
    void pessoaGetOneTest() throws Exception {
        //arrange
        pessoaPostDTO = PessoaPostDTO.builder()
                .nome("Naruto Uzumaki")
                .email("naruto@365.gmail.com")
                .cpf("11111111111")
                .nascimento("01/01/1999")
                .telefones(
                        new ArrayList<>(
                                Arrays.asList(
                                        Telefone.builder().numero("11111111111").build(),
                                        Telefone.builder().numero("22222222222").build()
                                )
                        )
                )
                .enderecos(
                        new ArrayList<>(
                                Arrays.asList(
                                        Endereco.builder()
                                                .numero(190)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build(),
                                        Endereco
                                                .builder()
                                                .numero(120)
                                                .complemento("lá mesmo")
                                                .logradouro(
                                                        Logradouro.builder()
                                                                .cep("000")
                                                                .nomelog("catu")
                                                                .tipo("casa")
                                                                .pais("brazil")
                                                                .estado("paraiba")
                                                                .cidade("queimadas")
                                                                .bairro("centro")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                )
                .profissao("Sapateiro")
                .build();

        Pessoa pessoaInicial = pessoaRepository.save(modelMapper.map(pessoaPostDTO, Pessoa.class));

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.get(URL + "/" + pessoaInicial.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pessoaPostDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Pessoa pessoaResultado = objectMapper.readValue(resultado, Pessoa.class);
        //assert
        assertAll(
                () -> assertNotNull(pessoaResultado.getId()),
                () -> assertTrue(pessoaResultado.getId() > 0),
                () -> assertNotNull(pessoaResultado.getNome()),
                () -> assertNotNull(pessoaResultado.getCpf()),
                () -> assertNotNull(pessoaResultado.getEmail()),
                () -> assertNotNull(pessoaResultado.getProfissao()),
                () -> assertEquals(pessoaResultado.getNome(), pessoaPostDTO.getNome()),
                () -> assertEquals(pessoaResultado.getCpf(), pessoaPostDTO.getCpf()),
                () -> assertEquals(pessoaResultado.getProfissao(), pessoaPostDTO.getProfissao()),
                () -> assertEquals(pessoaResultado.getTelefones(),pessoaPostDTO.getTelefones()),
                () -> assertEquals(pessoaResultado.getNascimento(), pessoaPostDTO.getNascimento()),
                () -> assertEquals(pessoaResultado.getEmail(), pessoaPostDTO.getEmail()),
                () -> assertEquals(pessoaResultado.getEnderecos(),pessoaPostDTO.getEnderecos())
        );
    }

}