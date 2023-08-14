package pl.zbadajsie.przychodnia.api.dto.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.api.dto.AnswerDto;
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.api.model.Question;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionDtoMapper {
    private final AnswerDtoMapper answerDtoMapper;
    public QuestionDto map(Question que){
        return QuestionDto.builder()
                .question(que.getQuestion())
                .description(que.getDescription())
                .multiple_correct_answers(que.getMultiple_correct_answers())
                .explanation(que.getExplanation())
                .tip(que.getTip())
                .category(que.getCategory())
                .difficulty(que.getDifficulty())
                .answers(getListAnswer(que))
                .build();
    }

    private List<AnswerDto> getListAnswer(Question que) {
        ArrayList<AnswerDto> arrayList = new ArrayList<>();
        arrayList.add(answerDtoMapper.mapA(que));
        arrayList.add(answerDtoMapper.mapB(que));
        arrayList.add(answerDtoMapper.mapC(que));
        arrayList.add(answerDtoMapper.mapD(que));
        arrayList.add(answerDtoMapper.mapE(que));
        arrayList.add(answerDtoMapper.mapF(que));
        return arrayList;
    }

}
