package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AbrigoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbrigoService abrigoService;

    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Deveria retornar código 400 para solicitação de adoção inválida")
    void codigo400() throws Exception {
        // ARRANGE

        String json = "{}";

        // ACT

        var response = mockMvc.perform(
                post("/abrigos")
                        .contentType("application/json")
                        .content(json)
        ).andReturn().getResponse();

        // ASSERT

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar código 200 para solicitação de adoção válida")
    void codigo200() throws Exception {
        // ARRANGE


        String json = """
                {
                        "nome": "PetLove",
                        "telefone": "32991255588",
                        "email": "petlove@gmail.com"
                }
                
                """;

        // ACT

        var response = mockMvc.perform(
                post("/abrigos")
                        .contentType("application/json")
                        .content(json)
        ).andReturn().getResponse();

        // ASSERT

        Assertions.assertEquals(200, response.getStatus());
    }

}
