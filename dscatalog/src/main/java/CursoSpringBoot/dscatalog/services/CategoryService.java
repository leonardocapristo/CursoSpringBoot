package CursoSpringBoot.dscatalog.services;

import CursoSpringBoot.dscatalog.dto.CategoryDTO;
import CursoSpringBoot.dscatalog.entities.Category;
import CursoSpringBoot.dscatalog.exceptions.DatabaseException;
import CursoSpringBoot.dscatalog.repositories.CategoryRepository;
import CursoSpringBoot.dscatalog.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
        Page<Category> list = repository.findAll(pageRequest);
        return list.map(x -> new CategoryDTO(x));
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
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
    try {
        Category entity = repository.getReferenceById(id);
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);

    }catch (EntityNotFoundException e){
        throw new ResourceNotFoundException("Id não encontrado: " + id);
    }

    }


    public void delete(Long id) {
        try{
            repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado: " + id));
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integridade de dados");
        }

    }


}