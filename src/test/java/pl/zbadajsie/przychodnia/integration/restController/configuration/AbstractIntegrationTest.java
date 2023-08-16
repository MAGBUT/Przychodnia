package pl.zbadajsie.przychodnia.integration.restController.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class AbstractIntegrationTest {
}
