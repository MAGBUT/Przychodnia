package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Specialization;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization,Long> {
    Specialization findByName(String name);
}
