package br.com.mgr.control.vacina.service.grupo;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dataprovider.repository.GruposPrioridadesRepository;
import org.junit.jupiter.api.AfterAll;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class GrupoPrioridadeServiceTest {

    @Autowired
    GrupoPrioridadeService service;

    @MockBean
    GruposPrioridadesRepository repository;

    @AfterAll
    private void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void saveTest(){
        BDDMockito.given(repository.save(Mockito.any(GrupoPrioridade.class)))
                .willReturn(getMockGrupoPrioridade());

        GrupoPrioridade grupoPrioridade = service.save(new GrupoPrioridade());

        assertEquals("Idoso 70", grupoPrioridade.getNome());
    }

    @Test
    public void findById() {
        BDDMockito.given(repository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(new GrupoPrioridade()));

        GrupoPrioridade grupoPrioridade = service.findById(2L);

        assertEquals(null, grupoPrioridade.getId());
    }



    public GrupoPrioridade getMockGrupoPrioridade(){

        return GrupoPrioridade.builder()
                .id(2L)
                .nome("Idoso 70")
                .descricao("Destinado a pessoas com idade acima de 70 anos")
                .build();
    }

}
