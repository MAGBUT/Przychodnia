package pl.zbadajsie.przychodnia.dto.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Specialization;
import pl.zbadajsie.przychodnia.repository.SpecializationRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorInfoDtoMapper {

    public DoctorInfoDto mapDoctorInfo(Doctor doctor) {
        return DoctorInfoDto.builder()
                .description(doctor.getDescription())
                .name(doctor.getPerson().getName())
                .surname(doctor.getPerson().getSurname())
                .specializations(new ArrayList<>(doctor.getSpecialization()))
                .build();
    }

}
