package pl.zbadajsie.przychodnia.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorFullInfoDto {
    private String firsName;
    private String surName;
    private String description;
    private String email;
    private String userName;
}
