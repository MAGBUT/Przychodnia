package pl.zbadajsie.przychodnia.dto.map;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DoctorDtoMapperTest {

    @InjectMocks
    private DoctorDtoMapper doctorDtoMapper;

    @Mock
    private DoctorRegistrationDto dto;

    @Mock
    private Doctor doctor;

    @Test
    void testMapDoctorDto() {
        // Given
        dto.setName("John");
        dto.setSurname("Doe");
        dto.setPesel("1234567890");

        // When
        Person person = doctorDtoMapper.mapPerson(dto, doctor);

        // Then
        assertEquals(dto.getName(), person.getName());
        assertEquals(dto.getSurname(), person.getSurname());
        assertEquals(dto.getPesel(), person.getPesel());
        assertEquals(doctor, person.getDoctor());
    }
}
