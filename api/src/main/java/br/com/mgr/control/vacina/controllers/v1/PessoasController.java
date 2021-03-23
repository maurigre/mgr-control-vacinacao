package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.controllers.v1.dto.PessoaDto;
import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dataprovider.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listarTodos(){
        return pessoaRepository.findAll();
    }



}
