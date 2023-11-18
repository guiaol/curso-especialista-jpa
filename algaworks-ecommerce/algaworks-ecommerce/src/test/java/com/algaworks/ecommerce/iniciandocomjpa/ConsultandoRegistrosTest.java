package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.*;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        Produto produto = em.find(Produto.class, 1);
//        Produto produto = em.getReference(Produto.class, 1);

//         System.out.println("Ainda não buscou!!!");

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = em.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        em.refresh(produto);

        Assertions.assertEquals("Kindle", produto.getNome());
    }

}
