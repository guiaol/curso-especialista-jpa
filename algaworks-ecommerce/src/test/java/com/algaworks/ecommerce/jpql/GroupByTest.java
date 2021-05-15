package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void condicionarAgrupamentoComHaving() {

        // total de vendas dentre as categorias que mais vendem
        String jpql = "select cat.nome, sum(ip.precoProduto) " +
                " from ItemPedido ip " +
                " join ip.produto pro" +
                " join pro.categorias cat" +
                " group by cat.id" +
                " having sum(ip.precoProduto) > 1500";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }

    @Test
    public void agruparEFiltrarResultado() {

        // total de vendas no mes
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
//                "from Pedido p " +
//                "where year(p.dataCriacao) = year(current_date) " +
//                "group by year(p.dataCriacao), month(p.dataCriacao)";

        // total de vendas por categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) " +
//                "from ItemPedido ip " +
//                "join ip.produto pro " +
//                "join pro.categorias c " +
//                "join ip.pedido p " +
//                "where year(p.dataCriacao) = year(current_date) " +
//                "and month(p.dataCriacao) = month(current_date)" +
//                "group by c.id";

        // total de vendas por cliente
        String jpql = "select c.nome, sum(ip.precoProduto) " +
                "from ItemPedido ip " +
                "join ip.pedido ped " +
                "join ped.cliente c " +
                "join ip.pedido p " +
                "where year(p.dataCriacao) = year(current_date) " +
                "and month(p.dataCriacao) >= (month(current_date) - 3) " +
                "group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }


    @Test
    public void agruparResultado() {
        // quantidade de produtos por categoria
        String jpql = "select c.nome, count(c.id) from Categoria c join c.produtos p group by c.id";

        // total de vendas no mes
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) from Pedido p group by year(p.dataCriacao), month(p.dataCriacao)";

        // total de vendas por categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c group by c.id";

        // total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.pedido ped join ped.cliente c group by c.id";
//        Fernando Medeiros, 5898.00
//        Marcos Mariano, 499.00

        // total de vendas por dia e por categoria
//        String jpql = "select day(pro.dataCriacao), sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c group by c.id";
//        String jpql = "select " +
//                " concat(year(p.dataCriacao), '/', month(p.dataCriacao), '/', day(p.dataCriacao)), " +
//                " concat(c.nome, ': ', sum(ip.precoProduto)) " +
//                " from ItemPedido ip join ip.pedido p join ip.produto pro join pro.categorias c " +
//                " group by year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao), c.id " +
//                " order by p.dataCriacao, c.nome ";


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }
}
