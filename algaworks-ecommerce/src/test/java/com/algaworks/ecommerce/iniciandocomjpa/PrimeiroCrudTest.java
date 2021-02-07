package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    // insert
    @Test
    public void inserirRegistro() {
        Cliente cliente = new Cliente();
//        cliente.setId(3); // comentando porque estamos utilizando IDENTITY
        cliente.setNome("Guilherme Oliveira");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }

    // select
    @Test
    public void buscarPorIdentificador() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertNotNull(cliente);
        Assert.assertEquals("Fernando Medeiros", cliente.getNome());
    }

    // select + update + select
    @Test
    public void atualizarRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        cliente.setNome("Guilherme Oliveira");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Guilherme Oliveira", clienteVerificacao.getNome());
    }

    // select + delete + select
    @Test
    public void removerRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(clienteVerificacao);
    }
}
