package br.com.mgr.control.vacina.service.pessoa.impl;

import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dataprovider.repository.PessoaRepository;
import br.com.mgr.control.vacina.exception.PessoaNotFoundException;
import br.com.mgr.control.vacina.service.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mauri Reis
 * @since 25/03/21
 */
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    PessoaRepository repository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Pessoa findById(Long id) throws PessoaNotFoundException{
        return repository.findById(id).orElseThrow(() -> new PessoaNotFoundException(id));
    }

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }
}
