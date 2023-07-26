package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface VisitRepository extends CrudRepository<Visit,Long> {
    @Query("SELECT v FROM Visit v JOIN v.person p WHERE p.id IN :personIds")
    Optional<List<Visit>> findByPersonIdIn(@Param("personIds") List<Integer> personIds);

    Optional<List<Visit>> findByPersonId(int idPatient);
}
