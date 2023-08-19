package pl.zbadajsie.przychodnia.integration.restController;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;

import java.util.List;

public interface QuestionControllerTestSupport {
    RequestSpecification requestSpecification();

    default List<QuestionDto> getQuestion() {
        Response response = requestSpecification()
                .get("/api/open/quiz")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .response();

        return response.jsonPath().getList(".", QuestionDto.class);
    }
}
