package CursoSpringBoot.dscatalog.controllers;

import CursoSpringBoot.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list = List.of(
                new Category(1L, "Books"),
                new Category(2L, "Electronics"),
                new Category(3L, "Computers"),
                new Category(4L, "Home Appliances")
        );

        return ResponseEntity.ok().body(list);
    }
}
