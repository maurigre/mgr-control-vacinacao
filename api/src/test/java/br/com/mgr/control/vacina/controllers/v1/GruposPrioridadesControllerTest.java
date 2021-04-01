package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dto.GrupoPrioridadeDto;
import br.com.mgr.control.vacina.dto.response.Response;
import br.com.mgr.control.vacina.exception.GrupoPrioridadeInvalidUpdateException;
import br.com.mgr.control.vacina.service.grupo.GrupoPrioridadeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class GruposPrioridadesControllerTest {


    final static String URL = "/api-vacinacao/v1/grupos";
    final static Long ID = 1L;
    final static String NOME = "Saude";
    final static String DESCRICAO = "Destinada a penas para pessoas da saude que estao na linha de frente";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GrupoPrioridadeService grupoPrioridadeService;


    @Test
    @Order(1)
    public void createGrupoPrioridadeSuccessTest() throws Exception {
        BDDMockito.given(grupoPrioridadeService.save(Mockito.any(GrupoPrioridade.class)))
                .willReturn(getMockGrupoPrioridade());

        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(getJsonPayLoad(ID, NOME, DESCRICAO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.nome").value(NOME))
                .andExpect(jsonPath("$.data.descricao").value(DESCRICAO));
    }

    @Test
    @Order(2)
    public void createGrupoPrioridadeInvalidParameterTest() throws Exception {
        BDDMockito.given(grupoPrioridadeService.save(Mockito.any(GrupoPrioridade.class)))
                .willReturn(getMockGrupoPrioridade());

        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(getJsonPayLoad(ID, null, DESCRICAO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.errors[0].field").value("nome"));
    }



    public GrupoPrioridade getMockGrupoPrioridade(){
        return GrupoPrioridade.builder()
                .id(ID)
                .nome(NOME)
                .descricao(DESCRICAO)
                .build();
    }

    public String getJsonPayLoad(Long id, String nome, String descricao) throws JsonProcessingException {

        GrupoPrioridadeDto dto = new GrupoPrioridadeDto();
        dto.setId(id);
        dto.setNome(nome);
        dto.setDescricao(descricao);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);

    }
}
