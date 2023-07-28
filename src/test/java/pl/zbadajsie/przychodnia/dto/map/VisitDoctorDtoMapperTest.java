package pl.zbadajsie.przychodnia.dto.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;

import java.time.LocalDate;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
class VisitDoctorDtoMapperTest {

    @InjectMocks
    private VisitDoctorDtoMapper visitDoctorDtoMapper;

    @Mock
    private Visit visit;

    @Mock
    private Person person;


    @Test
    void mapVisitDoctorDto() {
        // Given - Przygotowujemy dane
        Mockito.when(visit.getId()).thenReturn(1);
        Mockito.when(visit.getTime()).thenReturn(LocalTime.of(10, 0));
        Mockito.when(visit.getDate()).thenReturn(LocalDate.of(2023, 7, 31));
        Mockito.when(visit.getDescription()).thenReturn("Test visit");
        Mockito.when(visit.getAccept()).thenReturn(true);

        Mockito.when(person.getName()).thenReturn("John");
        Mockito.when(person.getSurname()).thenReturn("Doe");
        Mockito.when(person.getPesel()).thenReturn("12345678901");

        // When - Wywołujemy metodę, którą chcemy przetestować
        VisitDoctorDto visitDoctorDto = visitDoctorDtoMapper.map(visit, person);

        // Then - Sprawdzamy oczekiwany wynik
        Assertions.assertEquals(1, visitDoctorDto.getId());
        Assertions.assertEquals(LocalTime.of(10, 0), visitDoctorDto.getTime());
        Assertions.assertEquals(LocalDate.of(2023, 7, 31), visitDoctorDto.getDate());
        Assertions.assertEquals("Test visit", visitDoctorDto.getDescription());
        Assertions.assertEquals("John", visitDoctorDto.getPatientFirstName());
        Assertions.assertEquals("Doe", visitDoctorDto.getPatientLastName());
        Assertions.assertEquals("12345678901", visitDoctorDto.getPatientPesel());
        Assertions.assertTrue(visitDoctorDto.isAccept());
    }
}