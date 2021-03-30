package br.com.mgr.control.vacina.dataprovider.repository;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposPrioridadesRepository extends JpaRepository<GrupoPrioridade, Long> {
}
