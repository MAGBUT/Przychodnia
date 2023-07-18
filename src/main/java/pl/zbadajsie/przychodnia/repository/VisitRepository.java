package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Visit;

@Repository
public interface VisitRepository extends CrudRepository<Visit,Long> {
}
