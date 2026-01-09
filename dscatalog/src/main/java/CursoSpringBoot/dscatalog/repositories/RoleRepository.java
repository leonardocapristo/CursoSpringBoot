package CursoSpringBoot.dscatalog.repositories;


import CursoSpringBoot.dscatalog.entities.Role;
import CursoSpringBoot.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
