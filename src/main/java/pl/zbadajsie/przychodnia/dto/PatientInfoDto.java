package pl.zbadajsie.przychodnia.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"email"})
@NoArgsConstructor
public class PatientInfoDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
}
