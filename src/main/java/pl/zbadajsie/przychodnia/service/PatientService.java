package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorInfoDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.DoctorRepository;
import pl.zbadajsie.przychodnia.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientService {
    private final DoctorRepository doctorRepository;
    private final DoctorInfoDtoMapper doctorInfoDtoMapper;
    private final UserService userService;
    private final PatientDtoMapper patientDtoMapper;


    @Transactional
    public List<DoctorInfoDto> getAllDoctors() {
        List<DoctorInfoDto> doctorInfoDtoList = new ArrayList<>();
        List<Doctor> all = doctorRepository.findAll();
        all.forEach(doctor -> {
            doctorInfoDtoList.add(doctorInfoDtoMapper.mapDoctorInfo(doctor));
        });
        return doctorInfoDtoList;
    }

    @Transactional
    public PatientInfoDto getPatientInfo() {
        Person person = userService.getPerson();
        User user = person.getUser();
        PatientInfoDto dto = patientDtoMapper.map(person, user);
        return dto;
    }
}
