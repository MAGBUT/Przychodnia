package pl.zbadajsie.przychodnia.dto.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.DoctorDto;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Specialization;
import pl.zbadajsie.przychodnia.repository.SpecializationRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorDtoMapper {

    private final SpecializationRepository specializationRepository;

    public Person mapPerson(DoctorDto dto, Doctor doctor) {
        return Person.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .pesel(dto.getPesel())
                .doctor(doctor)
                .build();
    }

    public Doctor mapDoctor(DoctorDto dto) {
        return Doctor.builder()
                .description(dto.getDescription())
                .specialization(specializationSeter(dto.getSpecialization()))
                .build();
    }

    private Set<Specialization> specializationSeter(List<String> specialization) {
        Set<Specialization> specializations = new HashSet<>();
        specialization.forEach(name -> {
            specializations.add(specializationRepository.findByName(name));
        });
        return specializations;
    }
}
