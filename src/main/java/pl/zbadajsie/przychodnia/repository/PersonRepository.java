package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import pl.zbadajsie.przychodnia.model.Person;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
