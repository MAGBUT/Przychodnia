package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.VisitDto;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;

import java.util.Set;

@Service
public class VisitDtoMapper {
    public Visit map(VisitDto dto, Person person, Doctor doctor) {
        return Visit.builder()
                .person(Set.of(person, doctor.getPerson()))
                .description(dto.getDescription())
                .dateTime(dto.toOffsetDateTime())
                .build();
    }
}
