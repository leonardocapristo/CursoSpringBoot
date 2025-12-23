package CursoSpringBoot.dscatalog.services;

import CursoSpringBoot.dscatalog.dto.CategoryDTO;
import CursoSpringBoot.dscatalog.entities.Category;
import CursoSpringBoot.dscatalog.repositories.CategoryRepository;
import CursoSpringBoot.dscatalog.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();

        List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        /*

        List<CategoryDTO> listDto = new ArrayList<>();
        for (Category category : list) {
            listDto.add(new CategoryDTO(category));
        }

         */


        return listDto;

    }


    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {

        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        return new CategoryDTO(entity);
    }
}