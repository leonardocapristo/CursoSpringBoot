package CursoSpringBoot.dscatalog.services;

import CursoSpringBoot.dscatalog.dto.CategoryDTO;
import CursoSpringBoot.dscatalog.dto.ProductDTO;
import CursoSpringBoot.dscatalog.entities.Category;
import CursoSpringBoot.dscatalog.entities.Product;
import CursoSpringBoot.dscatalog.exceptions.DatabaseException;
import CursoSpringBoot.dscatalog.exceptions.ResourceNotFoundException;
import CursoSpringBoot.dscatalog.repositories.CategoryRepository;
import CursoSpringBoot.dscatalog.repositories.ProductRepository;
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
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAll();
        List<ProductDTO> listDto = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());

        /*
        List<ProductDTO> listDto = new ArrayList<>();
        for (Product category : list) {
            listDto.add(new ProductDTO(category));
        }
         */
        return listDto;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
    try {
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);

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

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());

        entity.getCategories().clear();

        for (CategoryDTO categoryDTO : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(categoryDTO.getId());
            entity.getCategories().add(category);
        }

    }


}