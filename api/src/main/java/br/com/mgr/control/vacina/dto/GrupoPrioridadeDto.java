package br.com.mgr.control.vacina.dto;

import br.com.mgr.control.vacina.dataprovider.model.GrupoPrioridade;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

/**
 * @author Mauri Reis
 * @since 30/03/21
 */
@Data
@ToString
@EqualsAndHashCode
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class GrupoPrioridadeDto extends RepresentationModel<GrupoPrioridadeDto> {

    private Long id;

    @NotNull(message = "Nome is not null")
    private String nome;

    @NotNull(message = "Descricao is not null")
    private String descricao;


    /**
     * Method to convert an GrupoPrioridade DTO to a GrupoPrioridade entity.
     * @return a <code>GrupoPrioridade</code> object
     */
    public GrupoPrioridade toGrupoPrioridade(){
        return new ModelMapper().map(this, GrupoPrioridade.class);
    }

    /**
     * Method to convert an GrupoPrioridade entity to a GrupoPrioridade DTO.
     * @return a <code>GrupoPrioridadeDto</code> object
     */
    public GrupoPrioridadeDto toGrupoPrioridadeDto(GrupoPrioridade grupoPrioridade){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(grupoPrioridade, this);
        return this;
    }
}
