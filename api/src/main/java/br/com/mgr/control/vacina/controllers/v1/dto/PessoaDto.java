package br.com.mgr.control.vacina.controllers.v1.dto;

import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class PessoaDto extends RepresentationModel<PessoaDto>{

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    private Integer idade;

    private LocalDate dataNascimento;


    /**
     * Method to convert an Pessoa DTO to a Pessoa entity.
     * @return a <code>Pessoa</code> object
     */
    public Pessoa toPessoa(){
        return new ModelMapper().map(this, Pessoa.class);
    }

    /**
     * Method to convert an Pessoa entity to a Pessoa DTO.
     * @return a <code>PessoaDto</code> object
     */
    public PessoaDto toPessoaDto(Pessoa pessoa) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(pessoa, this);
        return this;
    }
}
