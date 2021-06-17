package com.algaworks.ecommerce;

import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest extends EntityManagerFactoryTest {

    protected EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }
}
