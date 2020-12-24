package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void abrirEFecharATransacao() {

        Produto produto = new Produto();

        // inicia a transacao
        entityManager.getTransaction().begin();

        // operacoes que fazem alguma mudanca no banco de dados
//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        // finaliza a transacao - commit
        entityManager.getTransaction().commit();
    }

}
