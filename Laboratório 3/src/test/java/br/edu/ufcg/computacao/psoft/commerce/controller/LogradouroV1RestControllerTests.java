package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.logradouro.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.CustomErrorType;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.repository.LogradouroRepository;
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

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DisplayName("teste da entidade logradouro")
@AutoConfigureMockMvc
class LogradouroV1RestControllerTests {

    String URL = "/v1/logradouros";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MockMvc driver;

    @Autowired
    LogradouroRepository logradouroRepository;

    LogradouroPostDTO logradouroPostPutDTO;
    @BeforeEach
    void setUp() {
        this.logradouroRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("adiciona logradouro")
    void LogradouroPostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Logradouro logradouroResultado = objectMapper.readValue(resultado,Logradouro.class);
        //assert
        assertAll(
                () -> assertEquals(logradouroResultado.getNomelog(),logradouroPostPutDTO.getNomelog()),
                () -> assertEquals(logradouroResultado.getPais(),logradouroPostPutDTO.getPais()),
                () -> assertEquals(logradouroResultado.getTipo(),logradouroPostPutDTO.getTipo()),
                () -> assertEquals(logradouroResultado.getEstado(),logradouroPostPutDTO.getEstado()),
                () -> assertEquals(logradouroResultado.getBairro(),logradouroPostPutDTO.getBairro()),
                () -> assertEquals(logradouroResultado.getCidade(),logradouroPostPutDTO.getCidade()),
                () -> assertEquals(logradouroResultado.getCep(),logradouroPostPutDTO.getCep())

        );
    }

    @Test
    @DisplayName("adiciona logradouro sem nome dando erro")
    void LogradouroSemNomePostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O nome não pode ser vazio!"))
                )

        );
    }

    @Test
    @DisplayName("adiciona logradouro sem tipo dando erro")
    void LogradouroSemTipoPostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O tipo não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("adicionando logradouro sem cidade dando erro")
    void LogradouroSemCidadePostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("A cidade não pode ser vazia!"))
                )
        );
    }

    @Test
    @DisplayName("adiciona logradouro sem estado dando erro")
    void LogradouroSemEstadoPostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .pais("brasil")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O estado não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("adicionar logradouro sem bairro dando erro")
    void LogradouroSemBairroPostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O bairro não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("adiciona logradouro sem estado dando erro")
    void LogradouroSemPaisPostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .cep("00000000")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O pais não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("adiciona logradouro sem CEP dando erro")
    void LogradouroSemCepPostTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O cep não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("quando atualizar um logradouro")
    void LogradouroPutTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        Logradouro logradouroAntigo = logradouroRepository.save(modelMapper.map(logradouroPostPutDTO,Logradouro.class));
        logradouroPostPutDTO.builder()
                .tipo("predio")
                .nomelog("city")
                .bairro("vegas")
                .cidade("areia")
                .estado("Rio de Janeiro")
                .pais("Japão")
                .cep("11111111")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.put(URL + "/" + logradouroAntigo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();


        Logradouro logradouroResultado = objectMapper.readValue(resultado,Logradouro.class);
        //assert
        assertAll(
                () -> assertEquals(logradouroResultado.getNomelog(),logradouroPostPutDTO.getNomelog()),
                () -> assertEquals(logradouroResultado.getPais(),logradouroPostPutDTO.getPais()),
                () -> assertEquals(logradouroResultado.getTipo(),logradouroPostPutDTO.getTipo()),
                () -> assertEquals(logradouroResultado.getEstado(),logradouroPostPutDTO.getEstado()),
                () -> assertEquals(logradouroResultado.getBairro(),logradouroPostPutDTO.getBairro()),
                () -> assertEquals(logradouroResultado.getCidade(),logradouroPostPutDTO.getCidade()),
                () -> assertEquals(logradouroResultado.getCep(),logradouroPostPutDTO.getCep())

        );
    }

    @Test
    @DisplayName("Quando tentar deletar um logradouro com um id inexistente, dando erro")
    void logradouroInexistenteDeleteTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        Logradouro logradouroAntigo = logradouroRepository.save(modelMapper.map(logradouroPostPutDTO,Logradouro.class));
        logradouroRepository.deleteById(logradouroAntigo.getId());
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.delete(URL + "/" + logradouroAntigo.getId()))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Impossivel, esse logradouro não existe!",customErrorType.getMessage())
        );
    }

    @Test
    @DisplayName("Quando deletar um logradouro")
    void logradouroDeleteTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        Logradouro logradouroAntigo = logradouroRepository.save(modelMapper.map(logradouroPostPutDTO,Logradouro.class));
        Long logradouroSize = logradouroRepository.count();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.delete(URL + "/" + logradouroAntigo.getId()))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        //assert
        assertAll(
                () -> assertEquals(1L, logradouroSize - logradouroRepository.count())
        );
    }

    @Test
    @DisplayName("lendo logradouros do repositorio")
    void logradourosGetAll() throws Exception {
        //arrange
        Long TamanhoAnterior = logradouroRepository.count();
        LogradouroPostDTO lDTO1 = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        LogradouroPostDTO lDTO2 = LogradouroPostDTO.builder()
                .tipo("predio")
                .nomelog("las")
                .bairro("vegas")
                .cidade("Rio de Janeiro")
                .estado("Rio de Janeiro")
                .pais("Japão")
                .cep("11111111")
                .build();
        Logradouro l1 = logradouroRepository.save(modelMapper.map(lDTO1, Logradouro.class));
        Logradouro l2 = logradouroRepository.save(modelMapper.map(lDTO2, Logradouro.class));
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Collection<Logradouro> resultadoCollection = objectMapper.readValue(resultado, new TypeReference<>() {});

        //assert
        assertAll(
                () -> assertEquals(2L,logradouroRepository.count()-TamanhoAnterior),
                () -> assertTrue(resultadoCollection.contains(l2)),
                () -> assertTrue(resultadoCollection.contains(l1))
        );
    }

    @Test
    @DisplayName("lendo logradouro")
    void logradouroGetOneTest() throws Exception {
        //arrange
        logradouroPostPutDTO = LogradouroPostDTO.builder()
                .tipo("casa")
                .nomelog("catu")
                .bairro("centro")
                .cidade("campina grande")
                .estado("paraiba")
                .pais("brasil")
                .cep("00000000")
                .build();
        Logradouro logradouroAntigo = logradouroRepository.save(modelMapper.map(logradouroPostPutDTO,Logradouro.class));
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.get(URL + "/" + logradouroAntigo.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(logradouroPostPutDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Logradouro logradouroResultado = objectMapper.readValue(resultado, Logradouro.class);
        //assert
        assertAll(
                () -> assertEquals(logradouroResultado.getNomelog(),logradouroPostPutDTO.getNomelog()),
                () -> assertEquals(logradouroResultado.getPais(),logradouroPostPutDTO.getPais()),
                () -> assertEquals(logradouroResultado.getTipo(),logradouroPostPutDTO.getTipo()),
                () -> assertEquals(logradouroResultado.getEstado(),logradouroPostPutDTO.getEstado()),
                () -> assertEquals(logradouroResultado.getBairro(),logradouroPostPutDTO.getBairro()),
                () -> assertEquals(logradouroResultado.getCidade(),logradouroPostPutDTO.getCidade()),
                () -> assertEquals(logradouroResultado.getCep(),logradouroPostPutDTO.getCep())

        );

    }

}