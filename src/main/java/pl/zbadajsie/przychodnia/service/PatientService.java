package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorInfoDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
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


    @Transactional
    public List<DoctorInfoDto> getAllDoctors() {
        List<DoctorInfoDto> doctorInfoDtoList = new ArrayList<>();
        List<Doctor> all = doctorRepository.findAll();
        all.forEach(doctor -> {
            doctorInfoDtoList.add(doctorInfoDtoMapper.mapDoctorInfo(doctor));
        });
        return doctorInfoDtoList;
    }
}
