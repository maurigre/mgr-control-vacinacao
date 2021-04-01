package br.com.mgr.control.vacina.controllers.v1;


import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dto.PessoaDto;
import br.com.mgr.control.vacina.service.pessoa.PessoaService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.xml.crypto.Data;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mauri Reis
 * @since 31-03-2021
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class PessoasControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PessoaService service;

    static final Long ID = 1L;
    static final Long ID_GRUPO_PRIORITARIO = 1L;
    static final String NOME = "Rafael Nunes";
    static final String CPF = "99999999999";
    static final String EMAIL = "test@test.com.br";
    static final Integer IDADE = 40;
    static final String TELEFONE ="16997854542";
    static final Boolean IS_VACINADA = Boolean.TRUE;
    static final LocalDate DATA_NASCIMENTO = LocalDate.of(1980, 10, 01);
    static final String URL = "/api-vacinacao/v1/pessoas";

    @Test
    @Order(1)
    public void createPessoaSucessTest() throws Exception {

        BDDMockito.given(service.save(Mockito.any(Pessoa.class)))
                .willReturn(getMockPessoa());


        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayLoad(ID, NOME, CPF, DATA_NASCIMENTO, IDADE, EMAIL, TELEFONE, IS_VACINADA, ID_GRUPO_PRIORITARIO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.nome").value(NOME))
                .andExpect(jsonPath("$.data.cpf").value(CPF))
                .andExpect(jsonPath("$.data.dataNascimento").value(String.valueOf(DATA_NASCIMENTO)))
                .andExpect(jsonPath("$.data.idade").value(IDADE))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.telefone").value(TELEFONE))
                .andExpect(jsonPath("$.data.isVacinada").value(IS_VACINADA))
                .andExpect(jsonPath("$.data.grupoPrioridadeId").value(ID_GRUPO_PRIORITARIO));
    }

    @Test
    @Order(2)
    public void createPessoaInvalidParameterTest() throws Exception {
        BDDMockito.given(service.save(Mockito.any(Pessoa.class)))
                .willReturn(getMockPessoa());


        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(getJsonPayLoad(ID, NOME, CPF, DATA_NASCIMENTO, IDADE, "test",TELEFONE, IS_VACINADA, ID_GRUPO_PRIORITARIO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.errors[0].field").value("email"));

    }



    public Pessoa getMockPessoa(){

        GrupoPrioridade grupoPrioridade = GrupoPrioridade.builder()
                .id(1L)
                .nome("Saude")
                .descricao("Destinado a pessoas de saude que estão na linha de frente")
                .build();

        return Pessoa.builder()
                .id(ID)
                .nome(NOME)
                .cpf(CPF)
                .email(EMAIL)
                .idade(IDADE)
                .telefone(TELEFONE)
                .isVacinada(IS_VACINADA)
                .grupoPrioridade(grupoPrioridade)
                .dataNascimento(LocalDate.of(1980, 10, 01))
                .build();
    }


    public String getJsonPayLoad(Long id, String nome, String cpf, LocalDate dataNascimento, Integer idade,
                                 String email, String telefone, Boolean isVacinada, Long idGrupoPrioridade)
            throws JsonProcessingException {

        PessoaDto dto = new PessoaDto();
        dto.setId(id);
        dto.setNome(nome);
        dto.setCpf(cpf);
        dto.setDataNascimento(dataNascimento);
        dto.setIdade(idade);
        dto.setEmail(email);
        dto.setTelefone(telefone);
        dto.setIsVacinada(isVacinada);
        dto.setGrupoPrioridadeId(idGrupoPrioridade);


        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);

    }

}
