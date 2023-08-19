package pl.zbadajsie.przychodnia.api.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.service.QuestionService;

import java.util.List;
@RestController
@RequestMapping("/api/open/quiz")
@RequiredArgsConstructor
public class QuestionControllerApi {
    private final QuestionService questionService;
    @GetMapping
    ResponseEntity<?> getQuestion(){
        List<QuestionDto> questions = questionService.getQuestions();
        if(questions.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(questions);
    }
}
