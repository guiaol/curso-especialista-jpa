package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
// gera os metodos equals e hashcode. Tem apenas os atributos que estiverem marcados com @EqualsAndHashCode.Include
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
// customiza o nome das tabelas
@Table(name = "produto")
public class Produto {
    // inclui o id nos metodos equals e hashCode
    @EqualsAndHashCode.Include
    @Id 
    private Integer id;

    // @Column(name = "") - customiza o nome das colunas das tabelas
    private String nome;

    private String descricao;

    private BigDecimal preco;
}
