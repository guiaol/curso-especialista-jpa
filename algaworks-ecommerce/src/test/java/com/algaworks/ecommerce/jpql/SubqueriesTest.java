package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubqueriesTest extends EntityManagerTest {

    @Test
    public void pesquisarSubqueries() {

        // bons clientes versao 2. Que compram acima de 100 reais.
        String jpql = "select c " +
                "from Cliente c " +
                "where 500 < (" +
                "   select sum(p.total) from Pedido p where p.cliente = c" +
                ")";


        // bons clientes versao 1. Que compram acima de 500 reais.
//        String jpql = "select c " +
//                "from Cliente c " +
//                "where 500 < (" +
//                "   select sum(p.total) from c.pedidos p " +
//                ")";

        // todos os pedidos acima da media de vendas
//        String jpql = "select p " +
//                "from Pedido p " +
//                "where p.total > (" +
//                "   select avg(total) from Pedido" +
//                ")";

        // o produto ou os produtos mais caros da base
//        String jpql = "select p " +
//                "from Produto p " +
//                "where p.preco = (" +
//                "   select max(preco) from Produto" +
//                ")";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId() + " preço:" + obj.getNome()));
    }

}
