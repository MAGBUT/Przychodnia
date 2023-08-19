package pl.zbadajsie.przychodnia.testData;

import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;

import java.util.List;

public class DoctorDtoTestData {
    public static DoctorRegistrationDto someDoctorDto1(){
        return DoctorRegistrationDto.builder()
                .userName("userName11")
                .password("password1")
                .email("email@email.pl11")
                .description("description1")
                .specialization(List.of("dentysta"))
                .name("name1")
                .surname("surname1")
                .pesel("1234567890")
                .build();
    }
    public static DoctorRegistrationDto someDoctorDto2(){
        return DoctorRegistrationDto.builder()
                .userName("userName22")
                .password("password2")
                .email("email@email.pl22")
                .description("description2")
                .specialization(List.of("dentysta"))
                .name("name2")
                .surname("surname2")
                .pesel("1234567890")
                .build();
    }
    public static DoctorRegistrationDto someDoctorDto3(){
        return DoctorRegistrationDto.builder()
                .userName("userName33")
                .password("password3")
                .email("email@email.pl33")
                .description("description3")
                .specialization(List.of("dentysta"))
                .name("name3")
                .surname("surname3")
                .pesel("1234567890")
                .build();
    }
}
