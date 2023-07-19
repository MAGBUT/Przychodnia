package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.dto.map.PatientDtoMapper;
import pl.zbadajsie.przychodnia.dto.map.UserDtoMapper;
import pl.zbadajsie.przychodnia.exceptions.UserAlreadyExistException;
import pl.zbadajsie.przychodnia.model.Address;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.AddressRepository;
import pl.zbadajsie.przychodnia.repository.PersonRepository;
import pl.zbadajsie.przychodnia.repository.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientRegisterService {

    private final PersonRepository patientRepository;
    private final PatientDtoMapper patientDtoMapper;
    private final AddressRepository addressRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserRepository userRepository;


    @Transactional
    public void register (PatientRegistrationDto dto) {
        if(checkIfUserExist(dto.getEmail())){
            throw new UserAlreadyExistException("Uzytkownik o tym emailu już istnieje");
        }
        Address address = patientDtoMapper.mapAddress(dto);
        addressRepository.save(address);
        Person person = patientDtoMapper.mapPerson(dto,address);
        patientRepository.save(person);
        User user = userDtoMapper.map(dto,person);
        userRepository.save(user);
    }
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}

