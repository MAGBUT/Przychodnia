package pl.zbadajsie.przychodnia.dto;

import lombok.*;
import pl.zbadajsie.przychodnia.model.Specialization;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorInfoDto {
    private int id;
    private String name;
    private String surname;
    private String description;
    private List<Specialization> specializations;
}
