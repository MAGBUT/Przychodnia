package pl.zbadajsie.przychodnia.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoFromVisitDto {
    private int visitId;
    private NoteDto noteDto;
    private PrescriptionDto prescriptionDto;
    private ReferralDto referralDto;
}
