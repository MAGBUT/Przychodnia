package pl.zbadajsie.przychodnia.dto.map;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Role;
import pl.zbadajsie.przychodnia.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDtoMapper {
    private final PasswordEncoder passwordEncoder;

        public User map(PatientRegistrationDto dto, Person person){
            return User.builder()
                    .userName(dto.getUserName())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .email(dto.getEmail())
                    .active(true)
                    .roles(patientRole())
                    .person(person)
                    .build();
        }

    private Set<Role> patientRole() {
        Set<Role> roles = new HashSet<>();
        Role role = Role.builder()
                .role("PATIENT")
                .id(1)
                .build();
        roles.add(role);
        return roles;
    }

    public User map(DoctorRegistrationDto dto, Person person){
        return User.builder()
                .userName(dto.getUserName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .active(true)
                .person(person)
                .roles(doctorRole())
                .build();
    }

    private Set<Role> doctorRole() {
        Set<Role> roles = new HashSet<>();
        Role role = Role.builder()
                .role("DOCTOR")
                .id(2)
                .build();
        roles.add(role);
        return roles;
    }



}
