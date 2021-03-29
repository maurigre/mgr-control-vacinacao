package br.com.mgr.control.vacina.dataprovider.service.pessoa;


import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.exception.ApiException;
import br.com.mgr.control.vacina.exception.PessoaNotFoundException;

import java.util.List;

/**
 * @author Mauri Reis
 * @since
 */
public interface PessoaService {

    /**
     * @param pessoa
     * @return <code>Pessoa</code> Object
     */
    Pessoa save(Pessoa pessoa);

    void deleteById(Long id);

    /**
     * @param id
     * @return <code>Pessoa</code> Object
     */
    Pessoa findById(Long id) throws PessoaNotFoundException;

    /**
     * @return <code>List<Pessoa></code> Object
     */
    List<Pessoa> findAll();

}
