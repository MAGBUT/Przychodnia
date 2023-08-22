package pl.zbadajsie.przychodnia.unite.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.UserDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Specialization;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.DoctorRepository;
import pl.zbadajsie.przychodnia.repository.PersonRepository;
import pl.zbadajsie.przychodnia.repository.SpecializationRepository;
import pl.zbadajsie.przychodnia.repository.UserRepository;
import pl.zbadajsie.przychodnia.service.DoctorRegisterService;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorRegisterServiceTest {

    @InjectMocks
    private  DoctorRegisterService doctorRegisterService;
    @Mock
    private  DoctorRepository doctorRepository;
    @Mock
    private  DoctorDtoMapper doctorDtoMapper;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  UserDtoMapper userDtoMapper;
    @Mock
    private  SpecializationRepository specializationRepository;
    @Mock
    private  PersonRepository personRepository;

    @Test
    void register (){
        //give
        DoctorRegistrationDto dto = new DoctorRegistrationDto();
        Doctor doctor = new Doctor();
        Person person = new Person();
        User user = new User();

        when(doctorDtoMapper.mapDoctor(dto)).thenReturn(doctor);
        when(doctorDtoMapper.mapPerson(dto,doctor)).thenReturn(person);
        when(userDtoMapper.map(dto,person)).thenReturn(user);

        //when
        doctorRegisterService.register(dto);

        //then
        verify(doctorDtoMapper).mapDoctor(dto);
        verify(doctorDtoMapper).mapPerson(dto, doctor);
        verify(userDtoMapper).map(dto, person);

        verify(doctorRepository,times(2)).save(doctor);
        verify(personRepository).save(person);
        verify(userRepository).save(user);
    }

    @Test
    void checkEmail(){
        when(userRepository.findByEmail(anyString())).thenReturn(new User());

        doctorRegisterService.checkEmail("email");

        verify(userRepository).findByEmail(anyString());

    }

    @Test
    void checkUserName(){
        when(userRepository.findByUserName(anyString())).thenReturn(new User());

        doctorRegisterService.checkUserName("userName");

        verify(userRepository).findByUserName(anyString());

    }

    @Test
    void getAllSpecializations(){
        List<Specialization> list = List.of(Specialization.builder().name("Doktor").build());
        when(specializationRepository.findAll()).thenReturn(list);

        List<String> listReturn = doctorRegisterService.getAllSpecializations();

        verify(specializationRepository).findAll();
        Assertions.assertEquals(list.get(0).getName(),listReturn.get(0));

    }
    
}
