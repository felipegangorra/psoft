package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.ProdutoPostDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.*;
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
@AutoConfigureMockMvc
@DisplayName("Testes Produtos")
class ProdutoV1ControllerTest {
    @Autowired
    MockMvc driver;

    @Autowired
    ProdutoRepository produtoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Produto produto;

    @BeforeEach
    void setup() {
        produto = produtoRepository.criar(Produto.builder()
                .codigoDeBarras("111111111111")
                .fabricante("Naruto da Silva")
                .nome("Suco")
                .valor(10.00)
                .build()
        );
    }

    @AfterEach
    void tearDown() {

    }

    @Nested
    @DisplayName("Verificação de campos obrigatórios")
    class ProdutoVerificacaoCamposObrigatorios {

        @Test
        @DisplayName("Alteramos o nome do produto válido")
        void quandoAlteramosNomeDoProdutoValido() throws Exception {
            // Arrange
            produto.setNome("Coca");

            // Act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

            // Assert
            assertEquals("Coca", resultado.getNome());
        }

    }

    @Nested
    @DisplayName("Verificação CRUD")
    class ProdutoVerificacaoCRUD {

        final String URI_PRODUTOS = "/v1/produtos";
        ProdutoPostDTO produtoPostDTO;

        @BeforeEach
        void setup() {
            produtoPostDTO = ProdutoPostDTO.builder()
                    .fabricante("Sasuke Bezerra")
                    .nome("Café")
                    .codigoDeBarras("123456789456")
                    .valor(110.00)
                    .build();
            produtoPostDTO = ProdutoPostDTO.builder()
                    .fabricante("Sakura Cardoso")
                    .nome("Chá")
                    .codigoDeBarras("456789123258")
                    .valor(900.00)
                    .build();
        }

        @Test
        @DisplayName("Quando buscamos um produto salvo")
        void quandoBuscamosPorUmProdutoSalvo() throws Exception {
            // Arrange
            // Act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.get(URI_PRODUTOS + "/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produtoPostDTO)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            List<Produto> resultados = objectMapper.readValue(responseJsonString, new TypeReference<List<Produto>>(){});
            Produto resultado = resultados.stream().findFirst().orElse(Produto.builder().build());

            // Assert
            assertEquals(produto.getId().longValue(), resultado.getId().longValue());
            assertEquals(produto.getNome(), resultado.getNome());
            assertEquals(produto.getFabricante(), resultado.getFabricante());
            assertEquals(produto.getCodigoDeBarras(), resultado.getCodigoDeBarras());
        }

        @Test
        @DisplayName("Quando criamos um novo produto válido")
        void quandoCriarProdutoValido() throws Exception {
            // Arrange
            // Act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.post(URI_PRODUTOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produtoPostDTO)))
                    .andExpect(status().isCreated())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

            // Assert
            assertNotNull(resultado.getId());
            assertEquals(produtoPostDTO.getNome(), resultado.getNome());
            assertEquals(produtoPostDTO.getFabricante(), resultado.getFabricante());
            assertEquals(produtoPostDTO.getCodigoDeBarras(), resultado.getCodigoDeBarras());
        }

        @Test
        @DisplayName("Quando excluímos um produto")
        void quandoExcluimosProdutoValido() throws Exception {
            // Arrange
            // Act
            String responseJsonString = driver.perform(MockMvcRequestBuilders.delete(URI_PRODUTOS + "/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent()) // Codigo 204
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }

    }

}