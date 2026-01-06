package CursoSpringBoot.dscatalog.tests;

import CursoSpringBoot.dscatalog.dto.ProductDTO;
import CursoSpringBoot.dscatalog.entities.Category;
import CursoSpringBoot.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {
    public static Product createProduct(){
        Product product = new Product(1L,"Good Phone", "blablabla", 500.00,"http:imagem.com", Instant.parse("2020-07-13T20:50:07Z"));
        product.getCategories().add(new Category(2L, "Eletronics"));
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }


}
