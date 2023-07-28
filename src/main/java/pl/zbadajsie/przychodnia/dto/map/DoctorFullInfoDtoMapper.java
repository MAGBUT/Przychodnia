package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.DoctorFullInfoDto;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;

@Service
public class DoctorFullInfoDtoMapper {
    public DoctorFullInfoDto map(Doctor doctor, Person person, User user){
        return DoctorFullInfoDto.builder()
                .firsName(person.getName())
                .surName(person.getSurname())
                .description(doctor.getDescription())
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
