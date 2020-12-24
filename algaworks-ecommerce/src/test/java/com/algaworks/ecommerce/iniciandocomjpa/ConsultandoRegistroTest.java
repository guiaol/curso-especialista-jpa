package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultandoRegistroTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    // chamado antes dos testes serem executados
    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    // chamado depois dos testes serem executados
    @AfterClass
    public static void tearDownClass() {
        entityManagerFactory.close();
    }

    // Executado antes de cada teste
    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    // Executado depois de cada teste
    @After
    public void tearDown() {
        entityManager.close();
    }





}
