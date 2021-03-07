package com.algaworks.ecommerce.listener;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/*
  Quando o pedido for pago eu gero a nota fiscal daquele pedido.
 */
public class GerarNotalFiscalListener {

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalService.gerar(pedido);
        }
    }
}
