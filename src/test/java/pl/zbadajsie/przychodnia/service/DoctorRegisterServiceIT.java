package pl.zbadajsie.przychodnia.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Import(DoctorRegisterService.class)
@AllArgsConstructor
class DoctorRegisterServiceIT {

    @Autowired
    private DoctorRegisterService doctorRegisterService;


    @Test
    void register() {
        DoctorRegistrationDto dto = getDoctorRegistrationDto();

        doctorRegisterService.register(dto);

        boolean emailExist = doctorRegisterService.checkEmail(dto.getEmail());
        boolean userNameExist = doctorRegisterService.checkUserName(dto.getUserName());

        assertTrue(emailExist);
        assertTrue(userNameExist);

    }

    private static DoctorRegistrationDto getDoctorRegistrationDto() {
        return DoctorRegistrationDto.builder()
                .userName("userName")
                .password("password")
                .email("email@email.pl")
                .description("description")
                .specialization(List.of("dentysta"))
                .name("name")
                .surname("surname")
                .pesel("1234567890")
                .build();
    }
}