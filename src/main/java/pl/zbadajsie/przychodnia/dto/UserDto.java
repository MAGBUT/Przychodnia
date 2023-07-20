package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
public class UserDto {

    @Size(min = 2,max = 100)
    @NotBlank
    private String userName;
    @Size(min = 8,max = 100)
    @NotBlank
    private String password;
    @NotBlank
    @Email
    private String email;
}
