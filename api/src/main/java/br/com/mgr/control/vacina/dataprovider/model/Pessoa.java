package br.com.mgr.control.vacina.dataprovider.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode
@ToString
@Table(name = "pessoas_tbl")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    private Integer idade;

    private LocalDate dataNascimento;

    private Boolean isVacinada;

    @ManyToOne
    @JoinColumn(name="id_grupo_prioridade", referencedColumnName = "id")
    private GrupoPrioridade grupoPrioridade;
}
