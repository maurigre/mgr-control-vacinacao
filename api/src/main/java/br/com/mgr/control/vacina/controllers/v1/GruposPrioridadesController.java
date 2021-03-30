package br.com.mgr.control.vacina.controllers.v1;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import br.com.mgr.control.vacina.dataprovider.service.grupo.GrupoPrioridadeService;
import br.com.mgr.control.vacina.dto.GrupoPrioridadeDto;
import br.com.mgr.control.vacina.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
@RestController
@RequestMapping("/grupos")
public class GruposPrioridadesController {

    @Autowired
    GrupoPrioridadeService service;

    @GetMapping
    public ResponseEntity<Response<Object>> listaAll(){
        Response<Object> response = new Response<>();
        List<GrupoPrioridadeDto> dtos = new ArrayList<>();

        List<GrupoPrioridade> gruposPrioridades = service.findAll();
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

        GrupoPrioridade grupoPrioridade = service.findById(id);

        GrupoPrioridadeDto dto = new GrupoPrioridadeDto();
        dto.toGrupoPrioridadeDto(grupoPrioridade);

        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
