package pl.zbadajsie.przychodnia.integration.restController.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import pl.zbadajsie.przychodnia.api.service.QuestionGetService;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WireMockTestSupport {

    default void stubForPet(final WireMockServer wireMockServer, final Long petId) {
        wireMockServer.stubFor(get(urlPathEqualTo("/api/v1/questions?apiKey=" + QuestionGetService.apiKey + "&limit=10"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wiremock/queryForQuestion.json")
                        .withTransformers("response-template")));
    }
}
