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

        entityManager.getTransaction().begin();
        // sql : insert
        // faz um insert na base de dados e coloca o objeto no entityManager
        entityManager.persist(produto);
        // faz um insert no banco de dados
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, por isso nao fez um select no log
        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVeriicacao);
    }


    @Test
    public void abrirEFEcharATransacao() {
//        Produto produto = new Produto();

        // para realizar mudancas no banco de dados precisamos estar dentro de uma transacao
        // para aplicacoes web begin e commit sao automatizados
        // marco o inicio da transacao
        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        // marco o final da transacao
        entityManager.getTransaction().commit();
    }
}
