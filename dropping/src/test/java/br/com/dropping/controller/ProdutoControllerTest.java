package br.com.dropping.controller;

import br.com.dropping.domain.DadosCadastroSneaker;
import br.com.dropping.domain.Produto;
import br.com.dropping.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    void testSalvarTenisSucesso() throws Exception {

        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "Nike",
                "Air Max",
                "42",
                "Blue",
                "Male"
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(new Produto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testSalvarTenisFalhaComCampoBrandInvalido() throws Exception {

        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "",
                "Air Max",
                "42",
                "Blue",
                "Male"
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testSalvarTenisFalhaComCampoNameInvalido() throws Exception {

        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "Nike",
                "",
                "42",
                "Blue",
                "Male"
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testSalvarTenisFalhaComCampoSizeInvalido() throws Exception {

        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "Nike",
                "Air Max",
                "",
                "Blue",
                "Male"
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testSalvarTenisFalhaComCampoColorInvalido() throws Exception {

        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "Nike",
                "Air Max",
                "42",
                "",
                "Male"
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testSalvarTenisFalhaComCampoGenderInvalido() throws Exception {
        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "Nike",
                "Air Max",
                "42",
                "Blue",
                ""
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testSalvarTenisFalhaComTodosCamposInvalido() throws Exception {
        DadosCadastroSneaker dados = new DadosCadastroSneaker(
                "",
                "",
                "",
                "",
                ""
        );

        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dados)))
                .andExpect(status().isBadRequest());
    }
}








