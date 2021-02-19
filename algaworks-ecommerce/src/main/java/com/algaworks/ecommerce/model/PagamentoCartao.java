package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
