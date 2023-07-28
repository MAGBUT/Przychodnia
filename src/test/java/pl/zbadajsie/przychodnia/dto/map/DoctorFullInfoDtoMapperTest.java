package pl.zbadajsie.przychodnia.dto.map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.DoctorFullInfoDto;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DoctorFullInfoDtoMapperTest {

    @InjectMocks
    private DoctorFullInfoDtoMapper doctorFullInfoDtoMapper;
    @Mock
    private Doctor doctor;

    @Mock
    private Person person;

    @Mock
    private User user;

    @Test
    void map() {
// Given
        person.setName("John");
        person.setSurname("Doe");
        doctor.setDescription("Jestem spoko ziom");
        user.setUserName("Jacek");
        user.setEmail("Jacek@Jacek.pl");

        // When
        DoctorFullInfoDto doctorFullInfo = doctorFullInfoDtoMapper.map(doctor, person, user);

        // Then
        assertEquals(doctorFullInfo.getFirsName(), person.getName());
        assertEquals(doctorFullInfo.getSurName(), person.getSurname());
        assertEquals(doctorFullInfo.getDescription(), doctor.getDescription());
        assertEquals(doctorFullInfo.getUserName(), user.getUserName());
        assertEquals(doctorFullInfo.getEmail(), user.getEmail());

    }
}