package pl.zbadajsie.przychodnia.dto.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Role;
import pl.zbadajsie.przychodnia.model.User;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {

    @InjectMocks
    private UserDtoMapper userDtoMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PatientRegistrationDto patientRegistrationDto;

    @Mock
    private DoctorRegistrationDto doctorRegistrationDto;

    @Mock
    private Person person;

    @BeforeEach
    void setUp() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
    }

    @Test
    void mapPatient() {
        // Given

        Mockito.when(patientRegistrationDto.getUserName()).thenReturn("john_doe");
        Mockito.when(patientRegistrationDto.getPassword()).thenReturn("password");
        Mockito.when(patientRegistrationDto.getEmail()).thenReturn("john.doe@example.com");
        // When
        User user = userDtoMapper.map(patientRegistrationDto, person);

        // Then
        Assertions.assertEquals(patientRegistrationDto.getUserName(), user.getUserName());
        Assertions.assertEquals("encodedPassword", user.getPassword());
        Assertions.assertEquals(patientRegistrationDto.getEmail(), user.getEmail());
        Assertions.assertTrue(user.getActive());
        Assertions.assertEquals(person, user.getPerson());

        Set<Role> expectedRoles = new HashSet<>();
        expectedRoles.add(Role.builder().role("PATIENT").id(1).build());
        Assertions.assertEquals(expectedRoles, user.getRoles());
    }

    @Test
    void mapDoctor() {
        // Given
        Mockito.when(doctorRegistrationDto.getUserName()).thenReturn("dr_smith");
        Mockito.when(doctorRegistrationDto.getPassword()).thenReturn("password");
        Mockito.when(doctorRegistrationDto.getEmail()).thenReturn("dr.smith@example.com");


        // When
        User user = userDtoMapper.map(doctorRegistrationDto, person);

        // Then
        Assertions.assertEquals(doctorRegistrationDto.getUserName(), user.getUserName());
        Assertions.assertEquals("encodedPassword", user.getPassword());
        Assertions.assertEquals(doctorRegistrationDto.getEmail(), user.getEmail());
        Assertions.assertTrue(user.getActive());
        Assertions.assertEquals(person, user.getPerson());

        Set<Role> expectedRoles = new HashSet<>();
        expectedRoles.add(Role.builder().role("DOCTOR").id(2).build());
        Assertions.assertEquals(expectedRoles, user.getRoles());
    }
}