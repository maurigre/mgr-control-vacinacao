package br.com.mgr.control.vacina.dataprovider.service.pessoa.impl;

import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dataprovider.repository.PessoaRepository;
import br.com.mgr.control.vacina.dataprovider.service.pessoa.PessoaService;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
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
    public Pessoa findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }
}