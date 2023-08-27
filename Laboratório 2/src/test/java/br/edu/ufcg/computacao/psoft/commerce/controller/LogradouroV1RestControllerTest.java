package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.LogradouroPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DisplayName("Testes da Entidade Logradouro")
@AutoConfigureMockMvc
class LogradouroV1RestControllerTest {

    final String URL_TEMPLATE = "/v1/logradouros";

    @Autowired
    MockMvc driver;

    LogradouroPostDTO logradouroPostDTO;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        logradouroPostDTO = LogradouroPostDTO.builder()
                .tipo("Rua")
                .nome("Rua Teste")
                .bairro("Bairro Teste")
                .cidade("Cidade Teste")
                .estado("Estado Teste")
                .pais("País Teste")
                .cep("12345-678")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("Criação de Logradouro")
    class ValidacaoDeEntradas {

        @Test
        @DisplayName("Quando criar logradouro com dados válidos")
        void quandoCriarLogradouroValido() throws Exception {
            // Arrange & Act
            String resultadoStr = driver.perform(MockMvcRequestBuilders.post(URL_TEMPLATE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(logradouroPostDTO)))
                    .andExpect(status().isCreated())    // Código 201
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            Logradouro logradouroResultado = objectMapper.readValue(resultadoStr, Logradouro.class);

            // Assert
            assertNotNull(logradouroResultado.getId());
            assertTrue(logradouroResultado.getId() > 0);
            assertNotNull(logradouroResultado.getTipo());
            assertNotNull(logradouroResultado.getNome());
        }
    }

    @Nested
    @DisplayName("Leitura de Logradouro")
    class LeituraDeLogradouros {
        @Test
        @DisplayName("Buscando logradouro válido pelo id")
        void quandoBuscamosPorUmLogradouroSalvo() throws Exception {
            // Arrange
            String resultadoStr = driver.perform(MockMvcRequestBuilders.post(URL_TEMPLATE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(logradouroPostDTO)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();

            Logradouro logradouroSalvo = objectMapper.readValue(resultadoStr, Logradouro.class);

            // Act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.get(URL_TEMPLATE + "/" + logradouroSalvo.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            Logradouro resultado = objectMapper.readValue(responseJsonString, Logradouro.class);

            // Assert
            assertNotNull(resultado);
            assertEquals(logradouroSalvo.getId(), resultado.getId());
            assertEquals(logradouroSalvo.getTipo(), resultado.getTipo());
            assertEquals(logradouroSalvo.getNome(), resultado.getNome());
        }
    }

    @Nested
    @DisplayName("Atualização de Logradouro")
    class AtualizacaoDeLogradouros {
        @Test
        @DisplayName("Atualizando logradouro válido")
        void quandoAtualizaUmLogradouroSalvo() throws Exception {
            // Arrange
            String resultadoStr = driver.perform(MockMvcRequestBuilders.post(URL_TEMPLATE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(logradouroPostDTO)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();

            Logradouro logradouroSalvo = objectMapper.readValue(resultadoStr, Logradouro.class);

            // Atualiza informações do logradouro
            logradouroPostDTO.setTipo("Avenida");
            logradouroPostDTO.setNome("Avenida Atualizada");

            // Act
            String resultadoAtualizacaoStr = driver.perform(MockMvcRequestBuilders.put(URL_TEMPLATE + "/" + logradouroSalvo.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(logradouroPostDTO)))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            Logradouro logradouroAtualizado = objectMapper.readValue(resultadoAtualizacaoStr, Logradouro.class);

            // Assert
            assertNotNull(logradouroAtualizado);
            assertEquals(logradouroSalvo.getId(), logradouroAtualizado.getId());
            assertEquals(logradouroPostDTO.getTipo(), logradouroAtualizado.getTipo());
            assertEquals(logradouroPostDTO.getNome(), logradouroAtualizado.getNome());
        }
    }

    @Nested
    @DisplayName("Remoção de Logradouro")
    class RemocaoDeLogradouros {
        @Test
        @DisplayName("Removendo logradouro válido pelo id")
        void quandoRemovemosUmLogradouroSalvo() throws Exception {
            // Arrange
            String resultadoStr = driver.perform(MockMvcRequestBuilders.post(URL_TEMPLATE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(logradouroPostDTO)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();

            Logradouro logradouroSalvo = objectMapper.readValue(resultadoStr, Logradouro.class);

            // Act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.delete(URL_TEMPLATE + "/" + logradouroSalvo.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }
    }
}

