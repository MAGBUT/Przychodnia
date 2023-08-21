package pl.zbadajsie.przychodnia.integration.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.service.QuestionService;
import pl.zbadajsie.przychodnia.controller.PatientController;
import pl.zbadajsie.przychodnia.controller.QuizController;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(QuizController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class QuziControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    @WithMockUser
    void getInfoVisit() throws Exception {

        List<QuestionDto> questionDtos = List.of(new QuestionDto());

        when(questionService.getQuestions()).thenReturn(questionDtos);


        mockMvc.perform(MockMvcRequestBuilders.get("/quiz"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("question"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("questions"));

    }

}
