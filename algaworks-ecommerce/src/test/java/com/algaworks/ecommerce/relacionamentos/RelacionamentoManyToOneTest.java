package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import com.mysql.cj.protocol.x.XProtocol;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoItemPedido() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setId(new ItemPedidoId());

        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
        Assert.assertNotNull(itemPedidoVerificacao.getPedido());
        Assert.assertNotNull(itemPedidoVerificacao.getProduto());
    }


//    @Test
//    public void verificarRelacionamentoItemPedido() {
//        Cliente cliente = entityManager.find(Cliente.class, 1);
//        Produto produto = entityManager.find(Produto.class, 1);
//
//        Pedido pedido = new Pedido();
//        pedido.setStatus(StatusPedido.AGUARDANDO);
//        pedido.setDataCriacao(LocalDateTime.now());
//        pedido.setTotal(BigDecimal.TEN);
//        pedido.setCliente(cliente);
//
//        entityManager.persist(pedido);
//
//        entityManager.getTransaction().begin();
//
//        // pega o que esta na memoria do em e sincroniza com o banco de dados
//        // necessario para que a entidade pedido esteja sincronizada com a base de dados
//        entityManager.flush();
//
//        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setPrecoProduto(produto.getPreco());
//        itemPedido.setQuantidade(1);
//        itemPedido.setPedido(pedido);
//        itemPedido.setProduto(produto);
//        itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
//
//        entityManager.persist(itemPedido);
//        entityManager.getTransaction().commit();
//
//        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
//        Assert.assertNotNull(itemPedidoVerificacao.getPedido());
//        Assert.assertNotNull(itemPedidoVerificacao.getProduto());
//    }

    @Test
    public void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedido.getCliente());
    }
}
