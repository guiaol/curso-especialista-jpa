package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();

        // para resolver isso eu preciso excluir todos os item_pedido daquele pedido para depois remover o pedido
        // preciso remover todas as FK´s para depois remover o registro dono da FK
        pedido.getItens().forEach(i -> entityManager.remove(i));

        // nao posso remover pedido porque muitos item_pedido apontam para um pedido, no caso, item de pedido ficaria sem pedido
        // a FK protege da remocao do dado
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoVerificacao);
    }
}
