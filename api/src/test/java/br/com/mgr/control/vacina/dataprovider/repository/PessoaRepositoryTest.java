package br.com.mgr.control.vacina.dataprovider.repository;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class PessoaRepositoryTest  {

    @Autowired
    PessoaRepository repository;

    @Autowired
    GruposPrioridadesRepository gruposPrioridadesRepository;

    GrupoPrioridade grupoPrioridade;

    @BeforeAll
    public void setUp(){

        GrupoPrioridade grupoPrioridades = GrupoPrioridade.builder()
                .id(null)
                .nome("Saude")
                .descricao("Destinado a pessoas de saude que estão na linha de frente")
                .build();

        grupoPrioridade = gruposPrioridadesRepository.save(grupoPrioridades);

        Pessoa pessoa = Pessoa.builder()
                .id(null)
                .nome("Rafael Nunes")
                .cpf("99999999999")
                .email("test@test.com.br")
                .idade(40)
                .telefone("16997854542")
                .isVacinada(Boolean.TRUE)
                .grupoPrioridade(grupoPrioridade)
                .dataNascimento(LocalDate.of(1980, 10, 01))
                .build();

        repository.save(pessoa);
    }

    @Test
    public void saveTest(){

        Pessoa pessoa = Pessoa.builder()
                .id(null)
                .nome("Cleber soares")
                .cpf("88888888888")
                .email("cleber@test.com.br")
                .idade(38)
                .telefone("16980452002")
                .isVacinada(Boolean.TRUE)
                .grupoPrioridade(grupoPrioridade)
                .dataNascimento(LocalDate.of(1982, 10, 01))
                .build();

        Pessoa save = repository.save(pessoa);

        assertNotNull(save);

    }

    @Test
    public void findByIdTest(){

        Optional<Pessoa> pessoa = repository.findById(1L);

        assertFalse(pessoa.isEmpty());

    }

}
