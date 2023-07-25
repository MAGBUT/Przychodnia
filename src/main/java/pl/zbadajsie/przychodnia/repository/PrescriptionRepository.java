package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Prescription;
@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription,Long> {
}
