package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
import pl.zbadajsie.przychodnia.dto.map.VisitDoctorDtoMapper;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorService {
    private final UserService userService;
    private final VisitDoctorDtoMapper visitDoctorDtoMapper;

    @Transactional
    public Optional<List<VisitDoctorDto>> getVisit() {
        Person person = userService.getPerson();
        Set<Visit> visit = person.getVisit();
        if (visit.isEmpty()) {
            return Optional.empty();
        }
        List<VisitDoctorDto> list = visit.stream()
                .map(visit1 -> visitDoctorDtoMapper.map(visit1, getPersonFromVisit(visit1)))
                .toList();
        return Optional.of(list);
    }

    private Person getPersonFromVisit(Visit visit1) {
        Set<Person> person = visit1.getPerson();
        Optional<Person> first = person.stream()
                .filter(person1 -> person1.getDoctor() != null)
                .findFirst();
        return first.get();
    }
}
