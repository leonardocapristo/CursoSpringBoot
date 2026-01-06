package CursoSpringBoot.dscatalog.repositories;

import CursoSpringBoot.dscatalog.entities.Product;
import CursoSpringBoot.dscatalog.tests.Factory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;


import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long nonExistingId;
    private long existingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {

        nonExistingId = 1000L;
        existingId = 1L;
        countTotalProducts = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull(){
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        repository.deleteById(existingId);
        Optional<Product> result =  repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldDoNothingWhenIdDoesNotExists(){

        // Verifica que não existe
        Assertions.assertFalse(repository.existsById(nonExistingId));

        // Não deve lançar exceção
        Assertions.assertDoesNotThrow(() -> {
            repository.deleteById(nonExistingId);
        });
    }


    @Test
    public void findByIdShouldGetProductWhenIdExists(){
        Optional<Product> result =  repository.findById(existingId);
        Assertions.assertEquals(result.get().getId(), existingId);
    }

    @Test
    public void findByIdShouldReturnNullProductWhenIdDoesNotExists(){

        Optional<Product> result =  repository.findById(nonExistingId);

        Assertions.assertTrue(result.isEmpty());

    }


}
