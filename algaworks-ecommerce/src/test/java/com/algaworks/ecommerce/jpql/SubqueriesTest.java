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
    public void pesquisarComINExercicio() {

        // todos os pedidos que tenham produtos da categoria 2
        String jpql = "select p " +
                "from Pedido p " +
                "where p.id in (" +
                "   select p2.id " +
                "   from ItemPedido i2 " +
                "   join i2.pedido p2 " +
                "   join i2.produto pro2 " +
                "   join pro2.categorias cat2 " +
                "   where cat2.id = :categoria" +
                ")";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("categoria", 2);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComExists() {

        String jpql = "select p from Produto p where exists (" +
                "   select 1 from ItemPedido ip2 " +
                "   join ip2.produto p2" +
                "   where p2 = p" +
                ")";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }


    @Test
    public void pesquisarComIn() {

//        String jpql = "select distinct p " +
//                "from Pedido p " +
//                "join p.itens i " +
//                "join i.produto pro " +
//                "where pro.preco > 100";

        String jpql = "select p " +
                "from Pedido p " +
                "where p.id in (" +
                "   select p2.id " +
                "   from ItemPedido i2 " +
                "   join i2.pedido p2 " +
                "   join i2.produto pro2" +
                "   where pro2.preco > 100" +
                ")";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

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
