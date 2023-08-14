package pl.zbadajsie.przychodnia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.service.QuestionService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuestionService questionService;
    @GetMapping
    public String getQuestion(Model model){
        List<QuestionDto> questions = questionService.getQuestions();
        model.addAttribute("questions",questions);
        return "question";
    }
}
