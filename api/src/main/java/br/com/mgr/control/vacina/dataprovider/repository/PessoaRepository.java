package br.com.mgr.control.vacina.dataprovider.repository;

import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
