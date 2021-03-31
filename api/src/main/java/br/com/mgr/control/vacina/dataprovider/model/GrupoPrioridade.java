package br.com.mgr.control.vacina.dataprovider.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@ToString
@EqualsAndHashCode(of = {"id", "nome"})
@Table(name = "grupos_prioridades_tbl")
public class GrupoPrioridade implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

}
