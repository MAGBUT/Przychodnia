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
public class ReferralDto {

    private int id;

    @NotNull
    private String title;

    @NotNull
    private String description;

}
