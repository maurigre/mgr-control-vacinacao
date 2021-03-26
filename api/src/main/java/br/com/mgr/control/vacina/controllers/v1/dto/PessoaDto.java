package br.com.mgr.control.vacina.controllers.v1.dto;

import br.com.mgr.control.vacina.dataprovider.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class PessoaDto extends RepresentationModel<PessoaDto>{

    private Long id;

    @NotNull(message = "Nome não pode ser null")
    private String nome;

    @NotNull(message = "Cpf não pode ser null")
    private String cpf;

    @NotNull(message = "Telefone não pode ser null")
    private String telefone;

    @Email
    @NotNull(message = "E-mail não pode ser null")
    private String email;

    @Min(1)
    @NotNull(message = "Idade não pode ser null")
    private Integer idade;


    @NotNull(message = "Data Nascimento não pode ser null")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "en-US", timezone = "Brazil/East")
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
