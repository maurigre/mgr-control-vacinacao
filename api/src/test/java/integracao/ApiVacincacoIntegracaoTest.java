package integracao;

import br.com.mgr.control.vacina.VacinaApplication;
import br.com.mgr.control.vacina.dto.GrupoPrioridadeDto;
import br.com.mgr.control.vacina.dto.PessoaDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauri Reis
 * @since 01/04/21
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = {VacinaApplication.class})
public class ApiVacincacoIntegracaoTest {


    final static String URL_SERVER = "http://localhost:";

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void createGrupoPrioridadeSaudeTest(){

        //Cenario
        GrupoPrioridadeDto grupoPrioridadeDto = new GrupoPrioridadeDto();
        grupoPrioridadeDto.setId(null);
        grupoPrioridadeDto.setNome("Saude");
        grupoPrioridadeDto.setDescricao("Destinado para pessoas da saude que estao na linha de frente");


        HttpEntity<GrupoPrioridadeDto> httpEntity = new HttpEntity<>(grupoPrioridadeDto);

        //acao
        ResponseEntity<GrupoPrioridadeDto> responseEntity = this.restTemplate.exchange(
                URL_SERVER + port + "/api-vacinacao/v1/grupos",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<GrupoPrioridadeDto>() {
                });

        //validacao
        assertEquals(201, responseEntity.getStatusCodeValue());

    }
    @Test
    @Order(2)
    public void createPessoaMauriTest(){

        //cenario
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(null);
        pessoaDto.setNome("MAURI");
        pessoaDto.setCpf("99999999999");
        pessoaDto.setEmail("test@test.com.br");
        pessoaDto.setTelefone("16999999999");
        pessoaDto.setDataNascimento(LocalDate.of(1980, 10, 01));
        pessoaDto.setIdade(40);
        pessoaDto.setIsVacinada(Boolean.TRUE);
        pessoaDto.setGrupoPrioridadeId(1L);

        HttpEntity<PessoaDto> httpEntity = new HttpEntity<>(pessoaDto);

        //acao
        ResponseEntity<PessoaDto> responseEntity = this.restTemplate.exchange(
                URL_SERVER + port + "/api-vacinacao/v1/pessoas",
                HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<PessoaDto>() {});

        //validacao
        assertEquals(201, responseEntity.getStatusCodeValue());

    }


}
