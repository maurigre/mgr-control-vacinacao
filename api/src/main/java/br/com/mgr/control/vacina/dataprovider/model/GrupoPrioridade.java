package br.com.mgr.control.vacina.dataprovider.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Data
@EqualsAndHashCode(of = {"id", "nome"})
@Table(name = "grupos_prioridades_tbl")
public class GrupoPrioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

}
