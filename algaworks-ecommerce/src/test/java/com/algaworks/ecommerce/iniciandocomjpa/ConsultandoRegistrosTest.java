package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultandoRegistrosTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    // Chamado antes de executar os testes
    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    // Chamado depois de executar os testes
    @AfterClass
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    // Executado antes de cada teste
    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    // Execitadp depois de cada teste
    @After
    public void tearDown() {
        entityManager.close();
    }
}
