package com.algaworks.ecommerce.iniciandocomjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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

}
