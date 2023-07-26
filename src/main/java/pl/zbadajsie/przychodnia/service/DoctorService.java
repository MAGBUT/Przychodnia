package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.VisitDoctorDtoMapper;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.model.Visit;
import pl.zbadajsie.przychodnia.repository.PersonRepository;
import pl.zbadajsie.przychodnia.repository.UserRepository;
import pl.zbadajsie.przychodnia.repository.VisitRepository;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorService {
    private final UserService userService;
    private final VisitDoctorDtoMapper visitDoctorDtoMapper;
    private final PatientDtoMapper patientDtoMapper;
    private final UserRepository userRepository;
    private final VisitRepository visitRepository;


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

    @Transactional
    public Optional<List<PatientInfoDto>> getPatient() {
        Person doctor = userService.getPerson();
        Set<Visit> visit = doctor.getVisit();
        if(visit.isEmpty()){
            return Optional.empty();
        }
        List<Person> persons = new ArrayList<>();
        for (Visit visit1 : visit) {
            Optional<Person> first = visit1.getPerson().stream()
                    .filter(person -> person.getDoctor() == null)
                    .findFirst();
            first.ifPresent(persons::add);
        }
        List<PatientInfoDto> list = persons.stream()
                .map(person -> patientDtoMapper.map(person, person.getUser()))
                .distinct()
                .toList();
        return Optional.of(list);
    }

    @Transactional
    public Optional<List<VisitDoctorDto>> getUserVisit(String userName) {
        int id_doctor = userService.getPerson().getId();
        Optional<User> userByUserName = userRepository.findUserByUserName(userName);
        if(userByUserName.isEmpty()){
            return Optional.empty();
        }
        int id_patient = userByUserName.get().getPerson().getId();
        List<Integer> ids = Arrays.asList(id_doctor,id_patient);
        Optional<List<Visit>> visits = visitRepository.findByPersonIdIn(ids);
        if(visits.isEmpty()){
            return Optional.empty();
        }
        Person person = userByUserName.get().getPerson();
        List<VisitDoctorDto> visitDto = visits.get().stream()
                .map(visit -> visitDoctorDtoMapper.map(visit, person))
                .toList();
        return Optional.of(visitDto);
    }
}
