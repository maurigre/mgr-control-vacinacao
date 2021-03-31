package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dto.PessoaDto;
import br.com.mgr.control.vacina.dto.response.Response;
import br.com.mgr.control.vacina.exception.PessoaInvalidUpdateException;
import br.com.mgr.control.vacina.exception.PessoaNotFoundException;
import br.com.mgr.control.vacina.service.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Mauri Reis [
 * @since 23223121
 **/

@RestController
@RequestMapping("/api-vacinacao/v1/pessoas")
public class PessoasController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Response<List<PessoaDto>>> findAllPessoas() {
        List<Pessoa> pessoaList = pessoaService.findAll();
        List<PessoaDto> pessoaDtos = new ArrayList<>();

        pessoaList.stream().forEach(pessoa -> {
            PessoaDto dto = new PessoaDto();
            dto.toPessoaDto(pessoa)
                    .add(linkTo(methodOn(PessoasController.class).findByIdPessoa(dto.getId()))
                            .withSelfRel().expand());
            pessoaDtos.add(dto);
        });

        Response<List<PessoaDto>> response = new Response<>();
        response.setData(pessoaDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<PessoaDto>> findByIdPessoa(@PathVariable(value = "id") Long id) {

        Response<PessoaDto> response = new Response<>();
        PessoaDto pessoaDto = new PessoaDto();
        Pessoa pessoa = pessoaService.findById(id);
        pessoaDto.toPessoaDto(pessoa);
        pessoaDto.add(linkTo(methodOn(PessoasController.class).findAllPessoas()).withRel("Lista todos"));
        response.setData(pessoaDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Response<PessoaDto>> create(@Valid @RequestBody PessoaDto dto) {
        Pessoa pessoa = pessoaService.save(dto.toPessoa());
        Response<PessoaDto> response = new Response<>();
        dto.add(linkTo(methodOn(PessoasController.class).findByIdPessoa(pessoa.getId())).withSelfRel().expand());
        response.setData(dto.toPessoaDto(pessoa));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        Response<String> response = new Response<>();

        Pessoa pessoa = pessoaService.findById(id);
        pessoaService.deleteById(pessoa.getId());
        response.setData("Pessoa id="+ pessoa.getId() + " deleted");

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Response<PessoaDto>> update(@Valid @RequestBody PessoaDto dto) {
        Response<PessoaDto> response = new Response<>();

        if(dto.getId() == null) {
            throw new PessoaInvalidUpdateException(dto.getId());
        }

        Pessoa pessoaFind = pessoaService.findById(dto.getId());
        if (pessoaFind.getId().compareTo(dto.getId()) != 0 ) {
            throw new PessoaNotFoundException(dto.getId());
        }

        Pessoa pessoa = dto.toPessoa();
        Pessoa pessoaUpdate = pessoaService.save(pessoa);

        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.toPessoaDto(pessoaUpdate);
        pessoaDto.add(linkTo(methodOn(PessoasController.class).findByIdPessoa(dto.getId()))
                .withSelfRel().expand());

        response.setData(pessoaDto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
