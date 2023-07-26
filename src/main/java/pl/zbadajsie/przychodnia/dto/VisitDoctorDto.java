package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class VisitDoctorDto {
    private int id;
    private String patientFirstName;
    private String patientLastName;
    private String patientPesel;
    private boolean accept;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private int timezone;

    public OffsetDateTime toOffsetDateTime() {
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(timezone * 60);
        return OffsetDateTime.of(date, time, zoneOffset);
    }
}
