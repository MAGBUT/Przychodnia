package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;
}
