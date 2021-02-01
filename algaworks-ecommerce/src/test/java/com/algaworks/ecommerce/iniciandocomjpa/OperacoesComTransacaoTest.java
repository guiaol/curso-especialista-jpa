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

        // desanexa uma instância que está na memória do entityManager impedindo uma inserção, atualização ou remoção
        // no banco de dados
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
        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        // apenas comando insert
        // pega a referência da instância passada e a coloca no entityManager
        entityManager.persist(produtoPersist);
        // caso a referência sofra alguma alteração após o persit, ao finalizar a transação, o hibernate fará um update
        produtoPersist.setNome("Smartphone Two Plus ");
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, por isso nao fez um select no log
        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoVerificacaoPersist);




        Produto produtoMerge = new Produto();
        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        // merge serve para atualizar e inserir um novo objeto.
        // o método merge pega a instância passada, faz uma cópia dela, a retorna do método e a joga para o
        // entityManager gerenciar.
        // qualquer mofificação nessa instância não afetará a base de dados pois o que é passado para o entity manager
        // é uma cópia dessa instância e não a instância por si.
        // comandos sql:
        //          1 - select
        //              se não existir, faz um insert e se a referência retornada pelo merge sofrer alteração, faz update
        // pega a referência da instância passada e a coloca no entityManager
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, por isso nao fez um select no log
        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);


    }

    // select(hibernate) + insert
    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();
        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("Melhor qualidade de som");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        // o método merge serve tanto para atualizar quanto inserir
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, por isso nao fez um select no log
        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVeriicacao);
    }

    // select + update
    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        // Não precisamos do método merge porque o método find retorna um objeto produto e o coloca na memória
        // do entityManager. Qualquer alteração no objeto o entityManager percebe e ao finalizar a transação
        // faz um update do objeto na base de dados
        produto.setNome("Paperwhite 2ª geração");
        entityManager.getTransaction().commit();

        // limpa a memória do entityManager, com isso o método find vai ao banco imprimindo um select no log
        entityManager.clear();

        // busca o objeto no entityManager, como foi limpa a memória, faz um select na base para buscar o objeto novamente
        Produto produtoVeriicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Paperwhite 2ª geração", produtoVeriicacao.getNome());
    }

    // select(hibernate) + update
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

    // select + delete
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

    // insert
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
