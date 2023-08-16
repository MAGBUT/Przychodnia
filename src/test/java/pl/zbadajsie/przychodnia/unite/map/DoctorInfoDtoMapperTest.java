package pl.zbadajsie.przychodnia.unite.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorInfoDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Specialization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DoctorInfoDtoMapperTest {

    @InjectMocks
    private DoctorInfoDtoMapper doctorInfoDtoMapper;

    @Mock
    private Doctor doctor;

    @Mock
    private Person person;

    @Test
    void mapDoctorInfo() {
        //Give
        person.setName("jacek");
        person.setSurname("krzysiek");
        doctor.setId(3);
        doctor.setDescription("jestem spoko");
        Mockito.when(doctor.getPerson()).thenReturn(person);
        Set<Specialization> spec  = new HashSet<>();
        spec.add(Specialization.builder().name("Psychiatra").description("Słucham ludzi").build());
        spec.add(Specialization.builder().name("Pediatra").description("Badam ludzi").build());
        doctor.setSpecialization(spec);

        //When
        DoctorInfoDto dto = doctorInfoDtoMapper.mapDoctorInfo(doctor);

        //THEN
        assertEquals(dto.getName(), person.getName());
        assertEquals(dto.getSurname(), person.getSurname());
        assertEquals(dto.getId(), doctor.getId());
        assertEquals(dto.getDescription(), doctor.getDescription());
        assertEquals(dto.getSpecializations().size(), doctor.getSpecialization().size());
    }
}