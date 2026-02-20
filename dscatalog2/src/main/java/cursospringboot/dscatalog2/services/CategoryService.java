package cursospringboot.dscatalog2.services;

import cursospringboot.dscatalog2.dto.CategoryDTO;
import cursospringboot.dscatalog2.entities.Category;
import cursospringboot.dscatalog2.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> list = repository.findAll(pageable);
        return list.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        List<CategoryDTO> listDTO = new ArrayList<>();
        for (Category category : list){
            listDTO.add(new CategoryDTO(category));
        }
        return listDTO;
    }

    @Transactional
    public CategoryDTO findById(Long id){
        Category category = repository.findById(id).get();
        return new CategoryDTO(category);

    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        CategoryDTO categoryDTO = new CategoryDTO(repository.save(entity));
        return categoryDTO;
    }

    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) {
        Category entity = repository.findById(id).get();
        entity.setName(dto.getName());
        return new CategoryDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }



}
