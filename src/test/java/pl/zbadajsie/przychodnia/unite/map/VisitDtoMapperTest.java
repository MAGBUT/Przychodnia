package pl.zbadajsie.przychodnia.unite.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.VisitDto;
import pl.zbadajsie.przychodnia.dto.map.VisitDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class VisitDtoMapperTest {

    @InjectMocks
    private VisitDtoMapper visitDtoMapper;

    @Mock
    private VisitDto visitDto;

    @Mock
    private Person person;

    @Mock
    private Person person2;

    @Mock
    private Doctor doctor;

    @Mock
    private Visit visit;

    @Test
    void mapVisit() {
        // Given
        Mockito.when(visitDto.getDescription()).thenReturn("Test visit");
        Mockito.when(visitDto.toOffsetDateTime()).thenReturn(OffsetDateTime.of(2023, 7, 31, 10, 0, 0, 0, ZoneOffset.UTC));
        Mockito.when(doctor.getPerson()).thenReturn(person2);
        // When
        Visit visit = visitDtoMapper.map(visitDto, person, doctor);

        // Then
        Assertions.assertEquals("Test visit", visit.getDescription());
        Assertions.assertEquals(OffsetDateTime.of(2023, 7, 31, 10, 0, 0, 0, ZoneOffset.UTC), visit.getDateTime());
        Assertions.assertFalse(visit.getAccept());

        Set<Person> expectedPersons = new HashSet<>();
        expectedPersons.add(person);
        expectedPersons.add(doctor.getPerson());
        Assertions.assertEquals(expectedPersons, visit.getPerson());
    }

    @Test
    void mapVisitDto() {
        // Given
        Mockito.when(visit.getId()).thenReturn(1);
        Mockito.when(visit.getDate()).thenReturn(LocalDate.of(2023, 7, 31));
        Mockito.when(visit.getTime()).thenReturn(LocalTime.of(10, 0));
        Mockito.when(visit.getDescription()).thenReturn("Test visit");

        // When
        VisitDto visitDto = visitDtoMapper.map(visit);

        // Then
        Assertions.assertEquals(1, visitDto.getId());
        Assertions.assertEquals(LocalDate.of(2023, 7, 31), visitDto.getDate());
        Assertions.assertEquals(LocalTime.of(10, 0), visitDto.getTime());
        Assertions.assertEquals("Test visit", visitDto.getDescription());
    }
}