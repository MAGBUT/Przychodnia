package pl.zbadajsie.przychodnia.integration.restController;

import org.junit.jupiter.api.Test;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.integration.restController.configuration.RestAssureIntegrationTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static pl.zbadajsie.przychodnia.testData.PatientDtoTestData.somePatientDto3;

public class AdminControllerApiIT
        extends RestAssureIntegrationTestBase
        implements AdminControllerTestSupport, RegisterControllerTestSupport {

    @Test
    void deleteUser() {
        PatientRegistrationDto patientRegistrationDto1 = somePatientDto3();
        String userName = patientRegistrationDto1.getUserName();

        registerPatient(patientRegistrationDto1);

        String result = deleteUser(userName);

        System.out.println(result);

        assertThat(result, is(equalTo("User " + userName + " isDelete")));
    }


}
