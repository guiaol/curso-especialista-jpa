package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.*;

public class ConsultandoRegistroTest extends EntityManagerTest {
    @Test
    public void buscarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);

        // so vai no banco caso algum atributo do objeto for lido
//        Produto produto = entityManager.getReference(Produto.class, 1);
//        if(produto != null) {
//            System.out.println("fdfads");
//            produto.getNome();
//        }

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Fone");

        // faz uma nova consulta no banco e reinicia o objeto produto com o valor do banco
        entityManager.refresh(produto);
        Assert.assertEquals("Kindle", produto.getNome());

    }
}
