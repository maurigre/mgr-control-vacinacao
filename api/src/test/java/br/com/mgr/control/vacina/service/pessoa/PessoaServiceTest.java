package br.com.mgr.control.vacina.service.pessoa;


import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dataprovider.repository.PessoaRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mauri Reis
 * @since
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class PessoaServiceTest {

    @Autowired
    PessoaService service;

    @MockBean
    PessoaRepository repository;


    @AfterAll
    private void tearDown() {
        repository.deleteAll();
    }

    @Test
    @Order(1)
    public void saveTest() {
        BDDMockito.given(repository.save(Mockito.any(Pessoa.class)))
                .willReturn(getMockPessoa());

        Pessoa pessoa = service.save(new Pessoa());

        assertNotNull(pessoa);
        assertEquals("16997854542", pessoa.getTelefone());
    }

    @Test
    @Order(2)
    public void findByIdTest() {
        BDDMockito.given(repository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(new Pessoa()));

            Pessoa pessoa = service.findById(1L);

            assertEquals(null, pessoa.getId());
    }

    public Pessoa getMockPessoa(){

        GrupoPrioridade grupoPrioridade = GrupoPrioridade.builder()
                .id(1L)
                .nome("Saude")
                .descricao("Destinado a pessoas de saude que estão na linha de frente")
                .build();

        return Pessoa.builder()
                .id(1L)
                .nome("Rafael Nunes")
                .cpf("99999999999")
                .email("test@test.com.br")
                .idade(40)
                .telefone("16997854542")
                .isVacinada(Boolean.TRUE)
                .grupoPrioridade(grupoPrioridade)
                .dataNascimento(LocalDate.of(1980, 10, 01))
                .build();
    }
}
