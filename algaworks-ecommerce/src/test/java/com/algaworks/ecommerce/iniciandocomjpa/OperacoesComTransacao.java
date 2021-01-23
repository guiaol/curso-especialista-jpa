package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class OperacoesComTransacao extends EntityManagerTest {

    @Test
    public void abrirEFEcharATransacao() {

        Produto produto = new Produto();

        // para realizar mudancas no banco de dados precisamos estar dentro de uma transacao
        // para aplicacoes web begin e commit sao automatizados
        // marco o inicio da transacao
        entityManager.getTransaction().begin();

        entityManager.persist(produto);
        entityManager.merge(produto);
        entityManager.remove(produto);

        // marco o final da transacao
        entityManager.getTransaction().commit();
    }

}
