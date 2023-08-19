package pl.zbadajsie.przychodnia.integration.externalApi;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.service.QuestionGetService;
import pl.zbadajsie.przychodnia.integration.restController.configuration.RestAssureIntegrationTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class QuizControllerIT
        extends RestAssureIntegrationTestBase {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetQuestion() throws Exception {


        wireMockServer.stubFor(WireMock.get("/api/v1/questions?apiKey=" + QuestionGetService.apiKey + "&limit=10")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("wiremock/queryTest.json")));


        mockMvc.perform(MockMvcRequestBuilders.get("/quiz"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("question"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("questions"))
                .andDo(result -> {
                    List<QuestionDto> questions = (List<QuestionDto>) result.getModelAndView().getModel().get("questions");
                    assertThat(questions).isNotNull();
                    assertThat(questions).hasSize(10);
                });

    }
}