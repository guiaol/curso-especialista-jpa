package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import org.junit.*;

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
