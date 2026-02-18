package cursospringboot.dscatalog2.controllers;

import cursospringboot.dscatalog2.entities.Category;
import cursospringboot.dscatalog2.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    @RequestMapping(value = "/all")
    public ResponseEntity<List<Category>> findAll(){
        List<Category> cat = service.findAll();
        return ResponseEntity.ok().body(cat);
    }

}
