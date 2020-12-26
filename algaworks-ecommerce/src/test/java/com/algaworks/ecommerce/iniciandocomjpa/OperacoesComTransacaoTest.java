package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos");
        produto.setPreco(new BigDecimal(5000));

        // abre a transacao
        entityManager.getTransaction().begin();

        entityManager.persist(produto);

        // finaliza a trasacao
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerificacao);

    }


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
