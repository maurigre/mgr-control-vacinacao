package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.controllers.v1.dto.PessoaDto;
import br.com.mgr.control.vacina.controllers.v1.dto.response.Response;
import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import br.com.mgr.control.vacina.dataprovider.service.pessoa.PessoaService;
import br.com.mgr.control.vacina.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/pessoas")
public class PessoasController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> findAllPessoas() {
        List<Pessoa> pessoaList = pessoaService.findAll();
        List<PessoaDto> pessoaDtos = new ArrayList<>();

        pessoaList.stream().forEach(pessoa -> pessoaDtos.add(new PessoaDto().toPessoaDto(pessoa)));
        pessoaDtos.stream().forEach(dto -> {
            Long id = dto.getId();
            try {
                dto.add(linkTo(methodOn(PessoasController.class).findByIdPessoa(id)).withSelfRel().expand());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        });

        return new ResponseEntity<>(pessoaDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<PessoaDto>> findByIdPessoa(@PathVariable(value = "id") Long id) throws ApiException {

        Response<PessoaDto> response = new Response<>();
        PessoaDto pessoaDto = new PessoaDto();
        Pessoa pessoa = pessoaService.findById(id);
        pessoaDto.toPessoaDto(pessoa);
        pessoaDto.add(linkTo(methodOn(PessoasController.class).findAllPessoas()).withRel("Lista todos"));
        response.setData(pessoaDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    //Post
    @PostMapping
    public ResponseEntity<Response<PessoaDto>> create(@Valid @RequestBody PessoaDto dto, BindingResult result) {

        Response<PessoaDto> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> response.addErrorMsg(error.getDefaultMessage()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.setData(new PessoaDto());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {

    }

    //Put
    @PutMapping
    public void update() {


    }

}
