package cursospringboot.dscatalog2.repositories;

import cursospringboot.dscatalog2.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
