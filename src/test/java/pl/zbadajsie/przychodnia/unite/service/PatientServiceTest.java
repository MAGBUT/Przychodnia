package pl.zbadajsie.przychodnia.unite.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorInfoDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.DoctorRepository;
import pl.zbadajsie.przychodnia.service.PatientService;
import pl.zbadajsie.przychodnia.service.UserService;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorInfoDtoMapper doctorInfoDtoMapper;
    @Mock
    private UserService userService;
    @Mock
    private PatientDtoMapper patientDtoMapper;

    @Test
    void getAllDoctors(){
        List<Doctor> doctors = List.of(new Doctor());
        DoctorInfoDto dto = new DoctorInfoDto();

        when(doctorRepository.findAll()).thenReturn(doctors);
        when(doctorInfoDtoMapper.mapDoctorInfo(doctors.get(0))).thenReturn(dto);

        List<DoctorInfoDto> result = patientService.getAllDoctors();

        verify(doctorRepository).findAll();
        verify(doctorInfoDtoMapper).mapDoctorInfo(doctors.get(0));
        Assertions.assertEquals(result.get(0),dto);

    }

    @Test
    void getPatientInfo(){
        Person person = Person.builder().user(new User()).build();
        PatientInfoDto dto = new PatientInfoDto();

        when(userService.getPerson()).thenReturn(person);
        when(patientDtoMapper.map(any(), any())).thenReturn(dto);

        PatientInfoDto result = patientService.getPatientInfo();

        verify(userService).getPerson();
        verify(patientDtoMapper).map(any(),any());
        Assertions.assertEquals(result,dto);
    }
}
