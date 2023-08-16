package pl.zbadajsie.przychodnia.testData;

import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;

public class PatientDtoTestData {
    public static PatientRegistrationDto somePatientDto1(){
        return PatientRegistrationDto.builder()
                .userName("userName1")
                .password("password1")
                .email("email@email.pl1")
                .name("name1")
                .surname("surname1")
                .pesel("2345678998")
                .country("country1")
                .city("city1")
                .postalCode("33-331")
                .address("address1")
                .build();
    }
    public static PatientRegistrationDto somePatientDto2(){
        return PatientRegistrationDto.builder()
                .userName("userName2")
                .password("password2")
                .email("email@email.pl2")
                .name("name2")
                .surname("surname2")
                .pesel("2345678998")
                .country("country2")
                .city("city2")
                .postalCode("33-332")
                .address("address2")
                .build();
    }
    public static PatientRegistrationDto somePatientDto3(){
        return PatientRegistrationDto.builder()
                .userName("userName3")
                .password("password3")
                .email("email@email.pl3")
                .name("name3")
                .surname("surname3")
                .pesel("2345678998")
                .country("country3")
                .city("city3")
                .postalCode("33-333")
                .address("address3")
                .build();
    }
}
