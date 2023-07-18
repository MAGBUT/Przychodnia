package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String role);
}
