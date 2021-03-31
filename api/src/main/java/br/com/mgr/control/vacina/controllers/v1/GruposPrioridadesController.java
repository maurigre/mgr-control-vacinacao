package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dto.GrupoPrioridadeDto;
import br.com.mgr.control.vacina.dto.response.Response;
import br.com.mgr.control.vacina.exception.GrupoPrioridadeInvalidUpdateException;
import br.com.mgr.control.vacina.service.grupo.GrupoPrioridadeService;
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
 * @author Mauri Reis
 * @since 30/03/21
 */
@RestController
@RequestMapping("/api-vacinacao/v1/grupos")
public class GruposPrioridadesController {

    @Autowired
    GrupoPrioridadeService grupoPrioridadeService;

    @GetMapping
    public ResponseEntity<Response<Object>> findAll(){
        Response<Object> response = new Response<>();
        List<GrupoPrioridadeDto> dtos = new ArrayList<>();

        List<GrupoPrioridade> gruposPrioridades = grupoPrioridadeService.findAll();
        gruposPrioridades.stream().forEach(grupoPrioridade -> {
            GrupoPrioridadeDto dto = new GrupoPrioridadeDto();
            dto.toGrupoPrioridadeDto(grupoPrioridade);
            dto.add(linkTo(methodOn(GruposPrioridadesController.class)
                    .findById(grupoPrioridade.getId()))
                    .withSelfRel());
            dtos.add(dto);
        });

        response.setData(dtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<GrupoPrioridadeDto>> findById(@PathVariable Long id) {
        Response<GrupoPrioridadeDto> response = new Response<>();

        GrupoPrioridade grupoPrioridade = grupoPrioridadeService.findById(id);

        GrupoPrioridadeDto dto = new GrupoPrioridadeDto();
        dto.toGrupoPrioridadeDto(grupoPrioridade);

        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<GrupoPrioridadeDto>> create(@Valid @RequestBody GrupoPrioridadeDto dto) {
        Response<GrupoPrioridadeDto> response = new Response<>();
        GrupoPrioridade grupoPrioridade = grupoPrioridadeService.save(dto.toGrupoPrioridade());
        dto.add(linkTo(methodOn(GruposPrioridadesController.class)
                .findById(grupoPrioridade.getId()))
                .withSelfRel());

        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteById(@PathVariable Long id){
        Response<Object> response = new Response<>();

        GrupoPrioridade grupoPrioridade = grupoPrioridadeService.findById(id);
        grupoPrioridadeService.deleteById(grupoPrioridade.getId());
        response.setData("Grupo Prioridade id=" +grupoPrioridade.getId()+ " delected");

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Response<Object>> update(@RequestBody GrupoPrioridadeDto dto){
        Response<Object> response = new Response<>();

        if (dto.getId().equals(null)){
            throw new GrupoPrioridadeInvalidUpdateException(dto.getId());
        }

        GrupoPrioridade grupoPrioridadeFind = grupoPrioridadeService.findById(dto.getId());
        if (grupoPrioridadeFind.getId().compareTo(dto.getId()) != 0) {
            throw new GrupoPrioridadeInvalidUpdateException(dto.getId());
        }

        GrupoPrioridade grupoPrioridade = dto.toGrupoPrioridade();
        GrupoPrioridade grupoPrioridadeSave = grupoPrioridadeService.save(grupoPrioridade);

        GrupoPrioridadeDto grupoPrioridadeDto = new GrupoPrioridadeDto();
        grupoPrioridadeDto.toGrupoPrioridadeDto(grupoPrioridadeSave);
        grupoPrioridadeDto.add(linkTo(methodOn(GruposPrioridadesController.class)
                .findById(grupoPrioridadeDto.getId()))
                .withSelfRel()
                .expand());

        response.setData(grupoPrioridadeDto);

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
