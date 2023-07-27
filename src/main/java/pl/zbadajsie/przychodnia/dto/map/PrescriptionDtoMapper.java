package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.PrescriptionDto;
import pl.zbadajsie.przychodnia.model.Prescription;
import pl.zbadajsie.przychodnia.model.Referral;
import pl.zbadajsie.przychodnia.model.Visit;

@Service
public class PrescriptionDtoMapper {
    public PrescriptionDto map(Prescription prescription){
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .name(prescription.getName())
                .description(prescription.getDescription())
                .build();
    }

    public Prescription map(PrescriptionDto dto, Visit visit) {
        return Prescription.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .visit(visit)
                .build();
    }
}
