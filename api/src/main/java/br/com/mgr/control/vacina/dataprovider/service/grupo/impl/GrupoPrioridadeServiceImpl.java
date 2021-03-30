package br.com.mgr.control.vacina.dataprovider.service.grupo.impl;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dataprovider.repository.GruposPrioridadesRepository;
import br.com.mgr.control.vacina.dataprovider.service.grupo.GrupoPrioridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
@Service
public class GrupoPrioridadeServiceImpl implements GrupoPrioridadeService {

    @Autowired
    GruposPrioridadesRepository repository;

    @Override
    public GrupoPrioridade save(GrupoPrioridade grupoPrioridade) {
        return repository.save(grupoPrioridade);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public GrupoPrioridade findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<GrupoPrioridade> findAll() {
        return repository.findAll();
    }
}
