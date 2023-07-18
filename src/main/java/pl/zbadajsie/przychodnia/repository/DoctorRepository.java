package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
