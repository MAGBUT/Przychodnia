package pl.zbadajsie.przychodnia.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Question {
    private int id;
    private String question;
    private String description;
    private Map<String, String> answers;
    private Boolean multiple_correct_answers;
    private Map<String, String> correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<Map<String, String>> tags;
    private String category;
    private String difficulty;
}
