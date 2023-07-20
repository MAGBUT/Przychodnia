package pl.zbadajsie.przychodnia.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisitDto {
    @NotNull
    private int doctor_id;
    @Size(min = 10, max = 150)
    @NotNull
    private String description;
    @Past
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotNull
    private int timezone;

    public OffsetDateTime toOffsetDateTime() {
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(timezone * 60);
        return OffsetDateTime.of(date, time, zoneOffset);
    }

}
