package pl.zbadajsie.przychodnia.integration.restController;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.integration.restController.configuration.RestAssureIntegrationTestBase;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static pl.zbadajsie.przychodnia.testData.PatientDtoTestData.somePatientDto1;
import static pl.zbadajsie.przychodnia.testData.PatientDtoTestData.somePatientDto2;
import static pl.zbadajsie.przychodnia.testData.DoctorDtoTestData.someDoctorDto1;
import static pl.zbadajsie.przychodnia.testData.DoctorDtoTestData.someDoctorDto2;

public class RegistrationControllerRestAssuredIT
        extends RestAssureIntegrationTestBase
        implements RegisterControllerTestSupport {

    @Test
    @WithMockUser
    void addNewDoctorCorrectly() {
        PatientRegistrationDto patientRegistrationDto1 = somePatientDto1();
        PatientRegistrationDto patientRegistrationDto2 = somePatientDto2();


        PatientRegistrationDto patientRegistrationDto1Result = registerPatient(patientRegistrationDto1);
        PatientRegistrationDto patientRegistrationDto2Result = registerPatient(patientRegistrationDto2);

        assertThat(patientRegistrationDto1Result.getUserName(), is(equalTo(patientRegistrationDto1.getUserName())));
        assertThat(patientRegistrationDto2Result.getEmail(), is(equalTo(patientRegistrationDto2.getEmail())));

    }

    @Test
    @WithMockUser
    void addNewPatientCorrectly() {
        DoctorRegistrationDto doctorRegistrationDto1 = someDoctorDto1();
        DoctorRegistrationDto doctorRegistrationDto2 = someDoctorDto2();


        DoctorRegistrationDto doctorRegistrationDto1Result = registerDoctor(doctorRegistrationDto1);
        DoctorRegistrationDto doctorRegistrationDto2Result = registerDoctor(doctorRegistrationDto2);

        assertThat(doctorRegistrationDto1Result.getUserName(), is(equalTo(doctorRegistrationDto1.getUserName())));
        assertThat(doctorRegistrationDto2Result.getEmail(), is(equalTo(doctorRegistrationDto2.getEmail())));

    }
}
