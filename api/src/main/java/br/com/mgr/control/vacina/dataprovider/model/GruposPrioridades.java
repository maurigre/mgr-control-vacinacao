package br.com.mgr.control.vacina.dataprovider.model;

import lombok.*;

import javax.persistence.*;
import java.nio.file.LinkOption;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "grupos_prioridades_tbl")
public class GruposPrioridades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

}
