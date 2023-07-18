package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.DoctorDto;
import pl.zbadajsie.przychodnia.dto.map.DoctorDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.UserDtoMapper;
import pl.zbadajsie.przychodnia.exceptions.UserAlreadyExistException;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Specialization;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.DoctorRepository;
import pl.zbadajsie.przychodnia.repository.PersonRepository;
import pl.zbadajsie.przychodnia.repository.SpecializationRepository;
import pl.zbadajsie.przychodnia.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorRegisterService {
    private final DoctorRepository doctorRepository;
    private final DoctorDtoMapper doctorDtoMapper;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final SpecializationRepository specializationRepository;
    private final PersonRepository personRepository;


    @Transactional
    public void register (DoctorDto dto) {
        if(checkIfUserExist(dto.getEmail())){
            throw new UserAlreadyExistException("Uzytkownik o tym emailu już istnieje");
        }
        Doctor doctor = doctorDtoMapper.mapDoctor(dto);
        doctorRepository.save(doctor);
        Person person = doctorDtoMapper.mapPerson(dto,doctor);
        personRepository.save(person);
        doctor.setPerson(person);
        doctorRepository.save(doctor);
        User user = userDtoMapper.map(dto,person);
        userRepository.save(user);
    }
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }



    public List<String> getAllSpecializations() {
        List<Specialization> specializations = (List<Specialization>) specializationRepository.findAll();
        return specializations.stream()
                .map(Specialization::getName)
                .collect(Collectors.toList());
    }
}