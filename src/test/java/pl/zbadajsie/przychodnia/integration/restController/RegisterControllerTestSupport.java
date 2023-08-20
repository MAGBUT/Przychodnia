package pl.zbadajsie.przychodnia.integration.restController;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;

public interface RegisterControllerTestSupport {
    RequestSpecification requestSpecification();



    default DoctorRegistrationDto registerDoctor(final DoctorRegistrationDto doctorDto) {
        return requestSpecification()
                .body(doctorDto)
                .post("/api/open/registerForDoctor")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(DoctorRegistrationDto.class);
    }

    default PatientRegistrationDto registerPatient(final PatientRegistrationDto patientDto) {
        return requestSpecification()
                .body(patientDto)
                .post("/api/open/registerForPatient")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(PatientRegistrationDto.class);
    }
}
