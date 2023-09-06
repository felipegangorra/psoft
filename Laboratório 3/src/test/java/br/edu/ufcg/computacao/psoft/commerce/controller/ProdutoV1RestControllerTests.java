package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.dto.produto.ProdutoPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.CustomErrorType;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoRepository;
import br.edu.ufcg.computacao.psoft.commerce.service.produto.CodigoDeBarrasService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DisplayName("teste da entidade produtos")
@AutoConfigureMockMvc
class ProdutoV1RestControllerTests {

    final String URL = "/v1/produtos";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MockMvc driver;

    @Autowired
    ProdutoRepository produtoRepository;

    ProdutoPostPutDTO produtoPostPutDTO;
    @BeforeEach
    void setUp() {
        this.produtoRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("criando produto valido")
    void produtoPostTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Produto ProdutoResultado = objectMapper.readValue(resultado, Produto.class);
        //assert
        String finalCodigoBarras = codigoBarras;
        assertAll(
                () -> assertNotNull(ProdutoResultado.getId()),
                () -> assertTrue(ProdutoResultado.getId() > 0),
                () -> assertNotNull(ProdutoResultado.getNome()),
                () -> assertEquals(ProdutoResultado.getNome(),produtoPostPutDTO.getNome()),
                () -> assertEquals(ProdutoResultado.getValor(),produtoPostPutDTO.getValor()),
                () -> assertEquals(ProdutoResultado.getNome(),produtoPostPutDTO.getNome()),
                () -> assertEquals(ProdutoResultado.getCodbarras(), produtoPostPutDTO.getCodbarras()),
                () -> assertEquals(ProdutoResultado.getNomefab(),produtoPostPutDTO.getNomefab())
        );
    }

    @Test
    @DisplayName("criando um produto sem nome, dando erro")
    void produtoPostSemNomeTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);

        //assert
        assertAll(
                () -> assertEquals("Erros de validação.",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O nome não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("criando produto com codigo de barra invalido")
    void produtoPostCodigoInvalidoTest() throws Exception{
        //arrange
        String codigoBarras = "7811111110001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("O codigo de barras esta fora do padrao!",customErrorType.getMessage())
        );
    }


    @Test
    @DisplayName("criado um produto sem codigo de barras, dando erro")
    void produtoPostSemCodigoTest() throws Exception{
        //arrange
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação.",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O codigo de barras não pode ser vazio!"))
                )
        );
    }

    @Test
    @DisplayName("criando um produto sem valor, dando erro")
    void produtoPostSemValorTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .nome("postumas")
                .build();

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);

        //assert
        assertAll(
                () -> assertEquals("Erros de validação.",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("Não há produto gratuito!"))
                )
        );
    }


    @Test
    @DisplayName("criando um produto sem nome de fabricante, dando erro")
    void produtoPostSemNomeFabTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .valor(90.1F)
                .nome("postumas")
                .build();
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Erros de validação.",customErrorType.getMessage()),
                () -> assertEquals(1,customErrorType.getErrors().size()),
                () -> assertTrue(customErrorType.getErrors().stream().anyMatch(
                        (msg) -> msg.toLowerCase().contains("O nome do fabricante não pode ser vazio!"))
                )
        );
    }


    @Test
    @DisplayName("atualizando produto valido")
    void produtoPutTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();

        Produto produtoInicial = produtoRepository.save(modelMapper.map(produtoPostPutDTO,Produto.class));

        String novoCodigoBarras = "789999990002";
        novoCodigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(novoCodigoBarras));
        produtoPostPutDTO.setValor(100.0F);
        produtoPostPutDTO.setNomefab("cubinhas");
        produtoPostPutDTO.setNome("postuminhas");
        produtoPostPutDTO.setCodbarras(novoCodigoBarras);

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.put(URL + "/" + produtoInicial.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(produtoPostPutDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Produto ProdutoResultado = objectMapper.readValue(resultado, Produto.class);
        //assert
        assertAll(
                () -> assertNotNull(ProdutoResultado.getId()),
                () -> assertTrue(ProdutoResultado.getId() > 0),
                () -> assertNotNull(ProdutoResultado.getNome()),
                () -> assertEquals(ProdutoResultado.getNome(),produtoPostPutDTO.getNome()),
                () -> assertEquals(ProdutoResultado.getValor(),produtoPostPutDTO.getValor()),
                () -> assertEquals(ProdutoResultado.getNome(),produtoPostPutDTO.getNome()),
                () -> assertEquals(ProdutoResultado.getCodbarras(), produtoPostPutDTO.getCodbarras()),
                () -> assertEquals(ProdutoResultado.getNomefab(),produtoPostPutDTO.getNomefab())
        );
    }

    @Test
    @DisplayName("deletando produto que não existe, dando erro")
    void produtoDeleteInexistenteTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();

        Produto produtoInicial = produtoRepository.save(modelMapper.map(produtoPostPutDTO,Produto.class));
        produtoRepository.deleteById(produtoInicial.getId());

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.delete(URL + "/" + produtoInicial.getId()))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        CustomErrorType customErrorType = objectMapper.readValue(resultado, CustomErrorType.class);
        //assert
        assertAll(
                () -> assertEquals("Impossivel, esse produto não existe!",customErrorType.getMessage())
        );
    }

    @Test
    @DisplayName("deletando produto valido")
    void produtoDeleteTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();

        Produto produtoInicial = produtoRepository.save(modelMapper.map(produtoPostPutDTO,Produto.class));
        Long produtosSize = produtoRepository.count();

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.delete(URL + "/" + produtoInicial.getId()))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        //assert
        assertAll(
                () -> assertEquals(1L, produtosSize - produtoRepository.count())
        );
    }


    @Test
    @DisplayName("quando requisita todos os produtos. Então uma coleção é retornada")
    void produtoGetAllTest() throws Exception{
        //arrange
        Long TamanhoAnterior = produtoRepository.count();
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        ProdutoPostPutDTO aux1 = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();
        String novoCodigoBarras = "789999990002";
        novoCodigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(novoCodigoBarras));
        ProdutoPostPutDTO aux2 = ProdutoPostPutDTO.builder()
                .codbarras(novoCodigoBarras)
                .nomefab("cubinhas")
                .valor(11.0F)
                .nome("postuminhas")
                .build();
        Produto p1 = produtoRepository.save(modelMapper.map(aux1,Produto.class));
        Produto p2 = produtoRepository.save(modelMapper.map(aux2,Produto.class));
        //act
        String resultado = driver.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Collection<Produto> resultadoCollection= objectMapper.readValue(resultado, new TypeReference<>(){});
        //assert
        assertAll(
                () -> assertEquals(2L,produtoRepository.count()-TamanhoAnterior),
                () -> assertTrue(resultadoCollection.contains(p2)),
                () -> assertTrue(resultadoCollection.contains(p1))
        );
    }

    @Test
    @DisplayName("lendo produto valido")
    void produtoGetTest() throws Exception{
        //arrange
        String codigoBarras = "789999990001";
        codigoBarras += String.valueOf(CodigoDeBarrasService.getVerificador(codigoBarras));
        produtoPostPutDTO = ProdutoPostPutDTO.builder()
                .codbarras(codigoBarras)
                .nomefab("brascubas")
                .valor(90.1F)
                .nome("postumas")
                .build();

        Produto produto = produtoRepository.save(modelMapper.map(produtoPostPutDTO,Produto.class));

        //act
        String resultado = driver.perform(MockMvcRequestBuilders.get(URL + "/" + produto.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        Produto ProdutoResultado = objectMapper.readValue(resultado, Produto.class);
        //assert
        assertAll(
                () -> assertNotNull(ProdutoResultado.getId()),
                () -> assertTrue(ProdutoResultado.getId() > 0),
                () -> assertNotNull(ProdutoResultado.getNome()),
                () -> assertEquals(ProdutoResultado.getNome(),produtoPostPutDTO.getNome()),
                () -> assertEquals(ProdutoResultado.getValor(),produtoPostPutDTO.getValor()),
                () -> assertEquals(ProdutoResultado.getNome(),produtoPostPutDTO.getNome()),
                () -> assertEquals(ProdutoResultado.getCodbarras(), produtoPostPutDTO.getCodbarras()),
                () -> assertEquals(ProdutoResultado.getNomefab(),produtoPostPutDTO.getNomefab())
        );
    }
}