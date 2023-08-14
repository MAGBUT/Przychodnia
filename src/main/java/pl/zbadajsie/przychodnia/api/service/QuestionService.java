package pl.zbadajsie.przychodnia.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.dto.map.QuestionDtoMapper;
import pl.zbadajsie.przychodnia.api.model.Question;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDtoMapper questionDtoMapper;
    private final QuestionGetService questionGetService;


    public List<QuestionDto> getQuestions(){
        Optional<List<Question>> listQuestion = questionGetService.getListQuestion();
        if(listQuestion.isPresent()){
            return listQuestion.get().stream()
                    .map(questionDtoMapper::map)
                    .toList();
        }
        return null;
    }
}
