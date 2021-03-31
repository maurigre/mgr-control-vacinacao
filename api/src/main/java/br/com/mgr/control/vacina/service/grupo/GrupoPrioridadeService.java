package br.com.mgr.control.vacina.service.grupo;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;

import java.util.List;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
public interface GrupoPrioridadeService {

    /**
     * @param grupoPrioridade
     * @return <code>GruposPrioridades</code> is Object
     */
    GrupoPrioridade save(GrupoPrioridade grupoPrioridade);

    void deleteById(Long id);

    GrupoPrioridade findById(Long id);

    List<GrupoPrioridade> findAll();

}
