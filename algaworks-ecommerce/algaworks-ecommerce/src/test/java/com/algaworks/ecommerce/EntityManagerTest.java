package com.algaworks.ecommerce;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EntityManagerTest {

    protected static EntityManagerFactory emf;
    protected EntityManager em;

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
