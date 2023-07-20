package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.model.Doctor;

import java.util.ArrayList;

@Service
public class DoctorInfoDtoMapper {

    public DoctorInfoDto mapDoctorInfo(Doctor doctor) {
        return DoctorInfoDto.builder()
                .id(doctor.getId())
                .description(doctor.getDescription())
                .name(doctor.getPerson().getName())
                .surname(doctor.getPerson().getSurname())
                .specializations(new ArrayList<>(doctor.getSpecialization()))
                .build();
    }


}
