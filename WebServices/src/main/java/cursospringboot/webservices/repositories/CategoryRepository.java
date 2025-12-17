package cursospringboot.webservices.repositories;

import cursospringboot.webservices.entities.Category;
import cursospringboot.webservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
