package pl.zbadajsie.przychodnia.api.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String question;
    private String description;
    private Boolean multiple_correct_answers;
    private String explanation;
    private String tip;
    private String category;
    private String difficulty;
    private List<AnswerDto> answers;
}