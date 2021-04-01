package br.com.mgr.control.vacina.dataprovider.repository;


import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("Test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class GruposPrioridadesRepositoryTest  {

    @Autowired
    GruposPrioridadesRepository repository;

    @BeforeAll
    public void setUp(){

        GrupoPrioridade grupoPrioridades = GrupoPrioridade.builder()
                .id(null)
                .nome("Saude")
                .descricao("Destinado a pessoas de saude que estão na linha de frente")
                .build();

         repository.save(grupoPrioridades);
    }

    @Test
    @Order(1)
    public void saveTest(){

        GrupoPrioridade grupoPrioridades = GrupoPrioridade.builder()
                .id(null)
                .nome("Idoso 70")
                .descricao("Destinado a pessoas idosas com idade acima de 70 anos")
                .build();

        GrupoPrioridade grupoPrioridade = repository.save(grupoPrioridades);

        assertNotNull(grupoPrioridade);
    }




}
