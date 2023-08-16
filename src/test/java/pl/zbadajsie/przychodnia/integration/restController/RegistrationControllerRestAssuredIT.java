package pl.zbadajsie.przychodnia.integration.restController;

import org.junit.jupiter.api.Test;
import pl.zbadajsie.przychodnia.integration.restController.configuration.RestAssureIntegrationTestBase;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static pl.zbadajsie.przychodnia.testData.PatientDtoTestData.somePatientDto1;
import static pl.zbadajsie.przychodnia.testData.PatientDtoTestData.somePatientDto2;

public class RegistrationControllerRestAssuredIT
        extends RestAssureIntegrationTestBase
        implements RegisterControllerTestSupport {

    @Test
    void addNewDoctorCorrectly() {
        PatientRegistrationDto patientRegistrationDto1 = somePatientDto1();
        PatientRegistrationDto patientRegistrationDto2 = somePatientDto2();


        PatientRegistrationDto patientRegistrationDto1Result = registerPatient(patientRegistrationDto1);
        PatientRegistrationDto patientRegistrationDto2Result = registerPatient(patientRegistrationDto2);

        assertThat(patientRegistrationDto1Result.getUserName(), is(equalTo(patientRegistrationDto1.getUserName())));
        assertThat(patientRegistrationDto2Result.getEmail(), is(equalTo(patientRegistrationDto2.getEmail())));

    }
}
