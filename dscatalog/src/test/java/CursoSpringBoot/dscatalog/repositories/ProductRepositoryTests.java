package CursoSpringBoot.dscatalog.repositories;

import CursoSpringBoot.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        repository.deleteById(1L);
        Optional<Product> result =  repository.findById(1L);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAcessExceptionWhenIdDoesNotExists(){
        long nonExistingId = 1000L;
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->{
            repository.deleteById(nonExistingId);
        });
    }
}
