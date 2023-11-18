package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class ConsultandoRegistrosTest {

    private static EntityManagerFactory emf;

    private EntityManager em;

    @BeforeAll
    public static void setUpBeforeClass() {
        emf = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        emf.close();
    }

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        em.close();;
    }

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
