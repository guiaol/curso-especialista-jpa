package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class OperadoresLogicosTest extends EntityManagerTest {

    @Test
    public void usarOperadores() {
//        String jpql = "select p from Pedido p where p.total > 500 and p.status = 'AGUARDANDO' and p.cliente.id = 1";

//        String jpql = "select p from Pedido p where p.status = 'PAGO' or p.status = 'AGUARDANDO'";

        String jpql = "select p from Pedido p where p.status = 'PAGO' or p.status = 'AGUARDANDO' and p.total > 100";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

}
