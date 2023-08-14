package pl.zbadajsie.przychodnia.api.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String name;
    private String info;
    private Boolean correct;
}
