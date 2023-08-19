package pl.zbadajsie.przychodnia.integration.restController;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.service.QuestionGetService;
import pl.zbadajsie.przychodnia.integration.restController.configuration.RestAssureIntegrationTestBase;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionControllerApiIT
        extends RestAssureIntegrationTestBase
        implements QuestionControllerTestSupport {

    @Test
    void getsQuestionApi(){

        wireMockServer.stubFor(WireMock.get("/api/v1/questions?apiKey=" + QuestionGetService.apiKey + "&limit=10")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("wiremock/queryTest.json")));


        List<QuestionDto> questions = getQuestion();

        assertThat(questions).hasSize(10);
    }


}
