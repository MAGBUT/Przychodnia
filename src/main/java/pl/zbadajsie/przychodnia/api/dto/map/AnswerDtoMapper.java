package pl.zbadajsie.przychodnia.api.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.api.dto.AnswerDto;
import pl.zbadajsie.przychodnia.api.model.Question;

import java.util.Map;
import java.util.Set;

@Service
public class AnswerDtoMapper {
    public AnswerDto mapA (Question question){
        String name = "answer_a";
        String nameCorrect = "answer_a_correct";
        boolean b = Boolean.parseBoolean(question.getCorrect_answers().get(nameCorrect));
        return AnswerDto.builder()
                .name("A")
                .info(question.getAnswers().get(name))
                .correct(b)
                .build();
    }
    public AnswerDto mapB (Question question){
        String name = "answer_b";
        String nameCorrect = "answer_b_correct";
        return AnswerDto.builder()
                .name("B")
                .info(question.getAnswers().get(name))
                .correct(Boolean.parseBoolean(question.getCorrect_answers().get(nameCorrect)))
                .build();
    }
    public AnswerDto mapC (Question question){
        String name = "answer_c";
        String nameCorrect = "answer_c_correct";
        return AnswerDto.builder()
                .name("C")
                .info(question.getAnswers().get(name))
                .correct(Boolean.parseBoolean(question.getCorrect_answers().get(nameCorrect)))
                .build();
    }
    public AnswerDto mapD (Question question){
        String name = "answer_d";
        String nameCorrect = "answer_d_correct";
        return AnswerDto.builder()
                .name("D")
                .info(question.getAnswers().get(name))
                .correct(Boolean.parseBoolean(question.getCorrect_answers().get(nameCorrect)))
                .build();
    }
    public AnswerDto mapE (Question question){
        String name = "answer_e";
        String nameCorrect = "answer_e_correct";
        return AnswerDto.builder()
                .name("E")
                .info(question.getAnswers().get(name))
                .correct(Boolean.parseBoolean(question.getCorrect_answers().get(nameCorrect)))
                .build();
    }
    public AnswerDto mapF (Question question){
        String name = "answer_f";
        String nameCorrect = "answer_f_correct";
        return AnswerDto.builder()
                .name("F")
                .info(question.getAnswers().get(name))
                .correct(Boolean.parseBoolean(question.getCorrect_answers().get(nameCorrect)))
                .build();
    }

}
