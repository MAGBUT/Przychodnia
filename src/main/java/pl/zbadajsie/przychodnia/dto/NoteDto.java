package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String description;
}
