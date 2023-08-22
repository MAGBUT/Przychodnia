package pl.zbadajsie.przychodnia.unite.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.model.Visit;
import pl.zbadajsie.przychodnia.repository.UserRepository;
import pl.zbadajsie.przychodnia.repository.VisitRepository;
import pl.zbadajsie.przychodnia.dto.map.VisitDoctorDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.DoctorFullInfoDtoMapper;
import pl.zbadajsie.przychodnia.dto.DoctorFullInfoDto;
import pl.zbadajsie.przychodnia.service.DoctorService;
import pl.zbadajsie.przychodnia.service.UserService;



import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;
    @Mock
    private UserService userService;
    @Mock
    private VisitDoctorDtoMapper visitDoctorDtoMapper;
    @Mock
    private PatientDtoMapper patientDtoMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VisitRepository visitRepository;
    @Mock
    private DoctorFullInfoDtoMapper doctorFullInfoDtoMapper;

    @Test
    void getVisitNoVisits() {
        Person person = new Person();
        person.setVisit(Set.of());
        when(userService.getPerson()).thenReturn(person);

        Optional<List<VisitDoctorDto>> result = doctorService.getVisit();

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void getVisitWithVisits() {
        Person doctor = Person.builder().id(1).name("name1").surname("surname1").doctor(new Doctor()).build();
        Person person = Person.builder().id(2).name("name2").surname("surname2").build();
        Set<Visit> visits = Set.of(Visit.builder().description("visit1").person(Set.of(person,doctor)).build());
        person.setVisit(visits);
        doctor.setVisit(visits);

        when(userService.getPerson()).thenReturn(person);

        VisitDoctorDto visitDoctorDto = new VisitDoctorDto();
        when(visitDoctorDtoMapper.map(any(), any())).thenReturn(visitDoctorDto);

        Optional<List<VisitDoctorDto>> result = doctorService.getVisit();

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1, result.get().size());
    
    }

    @Test
    void getPatientVisitEmpty() {
        Person person = new Person();
        person.setVisit(Set.of());
        when(userService.getPerson()).thenReturn(person);

        Optional<List<PatientInfoDto>> result = doctorService.getPatient();

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void getPatient() {
        Person doctor = Person.builder().id(1).name("name1").surname("surname1").doctor(new Doctor()).build();
        Person person = Person.builder().id(2).name("name2").surname("surname2").build();
        Set<Visit> visits = Set.of(Visit.builder().description("visit1").person(Set.of(person,doctor)).build());
        person.setVisit(visits);
        doctor.setVisit(visits);
      
        when(userService.getPerson()).thenReturn(doctor);
        PatientInfoDto patientInfoDto = new PatientInfoDto();
        when(patientDtoMapper.map(any(), any())).thenReturn(patientInfoDto);

        Optional<List<PatientInfoDto>> result = doctorService.getPatient();

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1, result.get().size());
    }

     @Test
    void getUserVisitNotFindUser() {
        String userName = "userName";
        when(userService.getPerson()).thenReturn(new Person());
      
        Optional<List<VisitDoctorDto>> result = doctorService.getUserVisit(userName);

        Assertions.assertTrue(result.isEmpty());
        
    }
    @Test
    void getUserVisitNotFindVisit() {
        String userName = "userName";
        Person doctor = Person.builder().id(1).name("name1").surname("surname1").doctor(new Doctor()).build();
        Person person = Person.builder().id(2).name("name2").surname("surname2").build();
        Optional<User> user = Optional.of(User.builder().person(person).build());
        Set<Visit> visits = Set.of();
        person.setVisit(visits);
        doctor.setVisit(visits);

        when(userService.getPerson()).thenReturn(doctor);
        when(userRepository.findUserByUserName(userName)).thenReturn(user);
       
        Optional<List<VisitDoctorDto>> result = doctorService.getUserVisit(userName);

        Assertions.assertTrue(result.isEmpty());
        
    }

    @Test
    void getUserVisit() {
        String userName = "userName";
        Person doctor = Person.builder().id(1).name("name1").surname("surname1").doctor(new Doctor()).build();
        Person person = Person.builder().id(2).name("name2").surname("surname2").build();
        Optional<User> user = Optional.of(User.builder().person(person).build());
        Set<Visit> visits = Set.of(Visit.builder().description("visit1").person(Set.of(person,doctor)).build());
        person.setVisit(visits);
        doctor.setVisit(visits);

        when(userService.getPerson()).thenReturn(doctor);
        when(userRepository.findUserByUserName(userName)).thenReturn(user);
        when(visitDoctorDtoMapper.map(any(), any())).thenReturn(new VisitDoctorDto());
       
        Optional<List<VisitDoctorDto>> result = doctorService.getUserVisit(userName);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1, result.get().size());
        
    }

    @Test
    void getVisitById() {
        Long id = 1L;
        Person doctor = Person.builder().id(1).name("name1").surname("surname1").doctor(new Doctor()).build();
        Person person = Person.builder().id(2).name("name2").surname("surname2").build();
        Optional<Visit> visit = Optional.of(Visit.builder().description("visit1").person(Set.of(person,doctor)).build());
        Set<Visit> visits = Set.of(visit.get());
        person.setVisit(visits);
        doctor.setVisit(visits);

        when(visitRepository.findById(id)).thenReturn(visit);
        when(visitDoctorDtoMapper.map(any(), any())).thenReturn(new VisitDoctorDto());
       
        Optional<VisitDoctorDto> result = doctorService.getVisitById(id);

        Assertions.assertTrue(result.isPresent());
        
    }

    @Test
    void getVisitByIdNotVisit() {
        Long id = 1L;
        

        when(visitRepository.findById(id)).thenReturn(Optional.empty());
 
       
        Optional<VisitDoctorDto> result = doctorService.getVisitById(id);

        Assertions.assertTrue(result.isEmpty());
        
    }

    @Test
    void getDoctorInfo() {
        Person doctor = Person.builder().id(1).name("name1").surname("surname1").doctor(new Doctor()).build();
        when(userService.getPerson()).thenReturn(doctor);
        DoctorFullInfoDto dto = new DoctorFullInfoDto();
        when(doctorFullInfoDtoMapper.map(any(), any(),any())).thenReturn(dto);

    
        DoctorFullInfoDto result = doctorService.getDoctorInfo();

        Assertions.assertEquals(result,dto);
        
    }
    

}
