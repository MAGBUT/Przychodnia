package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.DoctorFullInfoDto;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorFullInfoDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.VisitDoctorDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.model.Visit;
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
    private final DoctorFullInfoDtoMapper doctorFullInfoDtoMapper;


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
            Person first = getPatientFromVisit(visit1.getPerson());
            persons.add(first);
        }
        List<PatientInfoDto> list = persons.stream()
                .map(person -> patientDtoMapper.map(person, person.getUser()))
                .distinct()
                .toList();
        return Optional.of(list);
    }

    @Transactional
    public Optional<List<VisitDoctorDto>> getUserVisit(String userName) {
        Person doctor = userService.getPerson();
        Optional<User> userByUserName = userRepository.findUserByUserName(userName);
        if(userByUserName.isEmpty()){
            return Optional.empty();
        }
        Person patient = userByUserName.get().getPerson();
        Set<Visit> visitPatient = patient.getVisit();
        if(visitPatient.isEmpty()){
            return Optional.empty();
        }
        List<VisitDoctorDto> visitDto = visitPatient.stream()
                .filter(visit -> checkVisitDoctor(visit, doctor))
                .map(visit -> visitDoctorDtoMapper.map(visit, patient))
                .toList();

        return Optional.of(visitDto);
    }

    private boolean checkVisitDoctor(Visit visit, Person doctor) {
        Set<Person> person = visit.getPerson();
        long count = person.stream()
                .filter(person1 -> person1.equals(doctor))
                .count();
        return count > 0;
    }

    @Transactional
    public Optional<VisitDoctorDto> getVisitById(Long id) {
        Optional<Visit> byId = visitRepository.findById(id);
        if(byId.isEmpty()){
            return Optional.empty();
        }
        Visit visit = byId.get();
        Set<Person> personOnVisit = visit.getPerson();
        Person patient = getPatientFromVisit(personOnVisit);
        return Optional.of(visitDoctorDtoMapper.map(visit,patient));
    }

    private Person getPatientFromVisit(Set<Person> person) {
        Optional<Person> first = person.stream()
                .filter(person1 -> person1.getDoctor() == null)
                .findFirst();
        return first.get();
    }

    @Transactional
    public DoctorFullInfoDto getDoctorInfo() {
        Person person = userService.getPerson();
        User user = person.getUser();
        Doctor doctor = person.getDoctor();
        return doctorFullInfoDtoMapper.map(doctor,person,user);
    }
}
