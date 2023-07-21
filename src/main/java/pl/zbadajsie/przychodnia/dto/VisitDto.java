package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.*;
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
public class VisitDto {
    private int id;
    private int doctor_id;
    @Size(min = 10, max = 150)
    @NotNull
    private String description;
    @Future(message = "Data musi być z przyszłości")
    @NotNull(message = "Data nie może być pusta")
    private LocalDate date;
    @NotNull(message = "Godzina nie może być pusta")
    private LocalTime time;

    private int timezone;

    public OffsetDateTime toOffsetDateTime() {
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(timezone * 60);
        return OffsetDateTime.of(date, time, zoneOffset);
    }

}
