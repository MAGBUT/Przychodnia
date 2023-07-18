package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    @Size(min = 2, max = 100)
    @NotBlank
    private String userName;

    @Size(min = 8, max = 100)
    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @Size(min = 8, max = 1000)
    @NotBlank
    private String description;

    @NotEmpty
    private List<String> specialization;

    @Size(max = 45, min = 2)
    @NotBlank
    private String name;

    @Size(max = 45, min = 2)
    @NotBlank
    private String surname;

    @Size(max = 10, min = 10)
    @NotBlank
    private String pesel;

}
