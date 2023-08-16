package pl.zbadajsie.przychodnia.unite.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.model.Address;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;

@ExtendWith(MockitoExtension.class)
class PatientDtoMapperTest {

    @InjectMocks
    private PatientDtoMapper patientDtoMapper;

    @Mock
    private PatientRegistrationDto patientRegistrationDto;

    @Mock
    private Address address;

    @Mock
    private Person person;

    @Mock
    private User user;

    @Test
    void mapPerson() {
        // Given
        Mockito.when(patientRegistrationDto.getName()).thenReturn("John");
        Mockito.when(patientRegistrationDto.getSurname()).thenReturn("Doe");
        Mockito.when(patientRegistrationDto.getPesel()).thenReturn("1234567890");

        // When
        Person person = patientDtoMapper.mapPerson(patientRegistrationDto, address);

        // Then
        Assertions.assertEquals(patientRegistrationDto.getName(), person.getName());
        Assertions.assertEquals(patientRegistrationDto.getSurname(), person.getSurname());
        Assertions.assertEquals(patientRegistrationDto.getPesel(), person.getPesel());
        Assertions.assertEquals(address, person.getAddress());
    }

    @Test
    void mapAddress() {
        // Given
        Mockito.when(patientRegistrationDto.getCountry()).thenReturn("Country");
        Mockito.when(patientRegistrationDto.getCity()).thenReturn("City");
        Mockito.when(patientRegistrationDto.getPostalCode()).thenReturn("12345");
        Mockito.when(patientRegistrationDto.getAddress()).thenReturn("Address");

        // When
        Address address = patientDtoMapper.mapAddress(patientRegistrationDto);

        // Then
        Assertions.assertEquals(patientRegistrationDto.getCountry(), address.getCountry());
        Assertions.assertEquals(patientRegistrationDto.getCity(), address.getCity());
        Assertions.assertEquals(patientRegistrationDto.getPostalCode(), address.getPostalCode());
        Assertions.assertEquals(patientRegistrationDto.getAddress(), address.getAddress());
    }

    @Test
    void map() {
        // Given
        Mockito.when(person.getName()).thenReturn("John");
        Mockito.when(person.getSurname()).thenReturn("Doe");
        Mockito.when(user.getEmail()).thenReturn("john.doe@example.com");
        Mockito.when(user.getUserName()).thenReturn("johndoe");

        // When
        PatientInfoDto dto = patientDtoMapper.map(person, user);

        // Then
        Assertions.assertEquals(person.getName(), dto.getFirstName());
        Assertions.assertEquals(person.getSurname(), dto.getLastName());
        Assertions.assertEquals(user.getEmail(), dto.getEmail());
        Assertions.assertEquals(user.getUserName(), dto.getUserName());
    }
}