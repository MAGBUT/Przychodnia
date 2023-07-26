package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;

@Service
public class VisitDoctorDtoMapper {
    public VisitDoctorDto map(Visit visit, Person person){
        return VisitDoctorDto.builder()
                .id(visit.getId())
                .time(visit.getTime())
                .date(visit.getDate())
                .description(visit.getDescription())
                .patientFirstName(person.getName())
                .patientLastName(person.getSurname())
                .patientPesel(person.getPesel())
                .accept(visit.getAccept())
                .build();
    }
}
