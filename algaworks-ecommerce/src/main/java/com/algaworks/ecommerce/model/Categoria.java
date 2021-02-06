package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria")
public class Categoria {
    @EqualsAndHashCode.Include
    @Id
    // nesta estrategia usa a geracao de uma sequence. O myslq nao tem sequence, mas o hibernate consegue simular uma.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    // generator - nome do gerador da sequence
    // name - bate com o nomedo gerador da sequence
    // sequenceName - nome da tabela gerada pelo implementador do JPA ou o nome da sequence  existente no banco de dados
    // initialValue - valor inicial da sequence
    // allocationSize = 50 - aloca na memoria ate os proximos 50 ids, chegando nesse valor, vai ao banco buscar os proximos 50.
    //                  quando esta comecando, ele vai na tabela e busca o 0, so quando chega no 50 que novamente na tabela e assim por diante.
    @SequenceGenerator(name = "seq", sequenceName = "sequencia_chave_primaria") //, allocationSize = 50, initialValue = 500)
    private Integer id;

    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;
}
