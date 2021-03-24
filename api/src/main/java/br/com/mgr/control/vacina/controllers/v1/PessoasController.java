package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.controllers.v1.dto.PessoaDto;
import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dataprovider.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> findAllPessoas(){
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        List<PessoaDto> pessoaDtos = new ArrayList<>();

        if(pessoaList.isEmpty()) {
            //retornar error lista vazia

        } else {
            pessoaList.stream().forEach(pessoa -> pessoaDtos.add(new PessoaDto().toPessoaDto(pessoa)));
            pessoaDtos.stream().forEach(dto -> {
                Long id = dto.getId();
                dto.add(linkTo(methodOn(PessoasController.class).findByIdPessoa(id)).withSelfRel());
            });

        }
        return new ResponseEntity<>(pessoaDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> findByIdPessoa(@PathVariable(value = "id") Long id ){

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.add(linkTo(methodOn(PessoasController.class).findAllPessoas()).withRel("Lista todos"));
        pessoaDto.toPessoaDto(pessoaOptional.get());


        if (pessoaOptional.isPresent()) {
           // pessoaDto.toPessoaDto(pessoaOptional.get());
           // pessoaDto.add(linkTo(methodOn(PessoasController.class).findAllPessoas()).withRel("Lista todos"));
        }

        return new ResponseEntity<>(pessoaDto, HttpStatus.OK);


    }





}
