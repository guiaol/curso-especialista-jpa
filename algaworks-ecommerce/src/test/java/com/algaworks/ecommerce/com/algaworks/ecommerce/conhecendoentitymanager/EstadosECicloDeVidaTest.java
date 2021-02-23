package com.algaworks.ecommerce.com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        // Estado Transiente ou novo - uso de new para instanciar objetos
        Categoria categoriaNovo = new Categoria();

        // Estado Managed (Gerenciado) - passa o objeto de Transient para Managed
        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);


        // Estado Managed (Gerenenciado) - uso de find / query - coloca o objeto na memoria do em
        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        // Estado Removed (Removido) - remove o objeto da memoria do em
        entityManager.remove(categoriaGerenciada);

        // Estado Managed (Gerenciado) - volta com o objeto para a memoria do em
        entityManager.persist(categoriaGerenciada);

        // Estado Detached (Desanexado) - desanexa o objeto da memoria do em
        entityManager.detach(categoriaGerenciada);

    }
}
