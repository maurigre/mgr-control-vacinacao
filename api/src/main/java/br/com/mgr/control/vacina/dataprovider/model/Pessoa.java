package br.com.mgr.control.vacina.dataprovider.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id", "cpf"})
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

    public Pessoa(Long id) {
        this.id = id;
    }
}
