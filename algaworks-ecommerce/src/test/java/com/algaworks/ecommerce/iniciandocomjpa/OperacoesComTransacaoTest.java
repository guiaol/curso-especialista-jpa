package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void atualizarObjeto() {

        Produto produto = new Produto();
        // precisa ser igual da base de dados
        produto.setId(1);
        produto.setNome("Kindle PaperWhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        // o metodo merge faz uma cópia do obejto não gerenciado pelo entityManger,
        // o joga na memória do entityManager e o retorna.
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, como foi limpa a memória, faz um select na base para buscar o objeto novamente
        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVeriicacao);
        Assert.assertEquals("Kindle PaperWhite", produtoVeriicacao.getNome());

    }

    @Test
    public void removerObjeto() {
        // 1)
        //    Desta forma, o objeto produto nao esta gerenciado pelo entityManager.
        //    Assim, gera o erro informando que o objeto nao esta anexado (detached) ao entityManager.
        //    Tentou remover uma instância desanexada.
//        Produto produto = new Produto();
//        produto.setId(3);

        // 2)
        //   Usamos o metodo find para trazer o objeto do bd e para jogar a referência na memória do entityManger.
        //   Dessa forma, ele se torna gerenciado pelo entityManager e o remove consegue excluir o objeto.
        //   Remove exclui o objeto da memória do entityManager e o commit exclui da base de dados.
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVeriicacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVeriicacao);
    }


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
