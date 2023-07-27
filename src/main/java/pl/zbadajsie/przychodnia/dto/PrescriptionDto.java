package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {

    private Integer id;
    @Size(min = 5,max = 50,message = "Tytułu musi mieć od 5 do 50 znaków")
    private String name;
    @Size(min = 20, max = 500, message = "Opis musi mieć od 5 do 500 znaków")
    private String description;
}
