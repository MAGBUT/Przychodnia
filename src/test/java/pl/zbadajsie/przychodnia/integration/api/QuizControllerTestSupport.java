package pl.zbadajsie.przychodnia.integration.api;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zbadajsie.przychodnia.api.model.Question;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;

public interface QuizControllerTestSupport {

    RequestSpecification requestSpecification();

    default Question getQuestion(){
        return requestSpecification()
                .get("/quiz")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(Question.class);
    }
}
