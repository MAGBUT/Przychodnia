package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.PrescriptionDto;
import pl.zbadajsie.przychodnia.model.Prescription;

@Service
public class PrescriptionDtoMapper {
    public PrescriptionDto map(Prescription prescription){
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .name(prescription.getName())
                .description(prescription.getDescription())
                .build();
    }
}
