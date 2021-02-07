package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {
    @Test
    public void impedirOperacaoComBancoDeDados() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Paperwhite 2ª geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle", produtoVeriicacao.getNome());
    }

    @Test
    public void mostrarDiferencaPersistMerge() {
        Produto produtoPersist = new Produto();
//        produtoPersist.setId(5); // comentando porque estamos utilizando IDENTITY
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus ");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoVerificacaoPersist);




        Produto produtoMerge = new Produto();
//        produtoMerge.setId(6); // comentando porque estamos utilizando IDENTITY
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);
    }

    // Com a estrategia GenerationType.IDENTITY, precisamos pegar a referencia que o metodo merge retorna para que
    // o codigo consiga executar.
    // select(hibernate) + insert
    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();
//        produto.setId(4); // comentando porque estamos utilizando IDENTITY
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("Melhor qualidade de som");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        // necessario devido a estrategia GenerationType.IDENTITY
        produto = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVeriicacao);
    }

    // select + update
    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Paperwhite 2ª geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Paperwhite 2ª geração", produtoVeriicacao.getNome());
    }

    // select(hibernate) + update
    // Com a estrategia GenerationType.IDENTITY, precisamos pegar a referencia que o metodo merge retorna para que
    // o codigo consiga executar.
    @Test
    public void atualizarObjeto() {
        Produto produto = entityManager.find(Produto.class, 1);
        // precisa ser igual da base de dados
        produto.setNome("Kindle PaperWhite");

        entityManager.getTransaction().begin();
        produto = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, como foi limpa a memória, faz um select na base para buscar o objeto novamente
        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle PaperWhite", produtoVerificacao.getNome());
    }

    // select + delete
    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVeriicacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVeriicacao);
    }

    // insert
    // Com a estrategia GenerationType.IDENTITY, precisamos pegar a referencia que o metodo merge retorna para que
    // o codigo consiga executar.
    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();
//        produto.setId(2); // comentando porque estamos utilizando IDENTITY
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
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
