package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Note;
@Repository
public interface NoteRepository extends CrudRepository<Note,Long> {
}
