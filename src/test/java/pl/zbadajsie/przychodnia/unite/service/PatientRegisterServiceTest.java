package pl.zbadajsie.przychodnia.unite.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.UserDtoMapper;
import pl.zbadajsie.przychodnia.model.Address;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.AddressRepository;
import pl.zbadajsie.przychodnia.repository.PersonRepository;
import pl.zbadajsie.przychodnia.repository.UserRepository;
import pl.zbadajsie.przychodnia.service.PatientRegisterService;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientRegisterServiceTest {

    @InjectMocks
    private PatientRegisterService patientRegisterService; 
    @Mock
    private PersonRepository patientRepository;
    @Mock
    private PatientDtoMapper patientDtoMapper;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private UserDtoMapper userDtoMapper;
    @Mock
    private UserRepository userRepository;

    @Test
    void register (){
        //give
        PatientRegistrationDto dto = new PatientRegistrationDto();
        Address address = new Address();
        Person person = new Person();
        User user = new User();

        when(patientDtoMapper.mapAddress(dto)).thenReturn(address);
        when(patientDtoMapper.mapPerson(dto,address)).thenReturn(person);
        when(userDtoMapper.map(dto,person)).thenReturn(user);

        //when
        patientRegisterService.register(dto);

        //then
        verify(patientDtoMapper).mapAddress(dto);
        verify(patientDtoMapper).mapPerson(dto, address);
        verify(userDtoMapper).map(dto, person);

        verify(addressRepository).save(address);
        verify(patientRepository).save(person);
        verify(userRepository).save(user);
    }

    @Test
    void checkUserName(){
        when(userRepository.findByUserName(anyString())).thenReturn(new User());

        patientRegisterService.checkUserName("userName");

        verify(userRepository).findByUserName(anyString());

    }

    @Test
    void checkEmail(){
        when(userRepository.findByEmail(anyString())).thenReturn(new User());

        patientRegisterService.checkEmail("email");

        verify(userRepository).findByEmail(anyString());

    }

    
}
