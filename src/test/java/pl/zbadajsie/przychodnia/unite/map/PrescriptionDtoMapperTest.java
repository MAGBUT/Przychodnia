package pl.zbadajsie.przychodnia.unite.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.PrescriptionDto;
import pl.zbadajsie.przychodnia.dto.ReferralDto;
import pl.zbadajsie.przychodnia.dto.map.PrescriptionDtoMapper;
import pl.zbadajsie.przychodnia.model.Prescription;
import pl.zbadajsie.przychodnia.model.Referral;
import pl.zbadajsie.przychodnia.model.Visit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrescriptionDtoMapperTest {

    @InjectMocks
    private PrescriptionDtoMapper prescriptionDtoMapper;

    @Mock
    private Prescription prescription;

    @Mock
    private PrescriptionDto prescriptionDto;

    @Mock
    private Visit visit;

    @Test
    void mapReferral() {
        // Given
        Mockito.when(prescription.getId()).thenReturn(1);
        Mockito.when(prescription.getName()).thenReturn("Title");
        Mockito.when(prescription.getDescription()).thenReturn("Description");

        // When
        PrescriptionDto dto = prescriptionDtoMapper.map(prescription);

        // Then
        Assertions.assertEquals(prescription.getId(), dto.getId());
        Assertions.assertEquals(prescription.getName(), dto.getName());
        Assertions.assertEquals(prescription.getDescription(), dto.getDescription());
    }

    @Test
    void map() {
        // Given
        Mockito.when(prescriptionDto.getName()).thenReturn("Title");
        Mockito.when(prescriptionDto.getDescription()).thenReturn("Description");

        // When
        Prescription dto = prescriptionDtoMapper.map(prescriptionDto, visit);

        // Then
        Assertions.assertEquals(prescriptionDto.getName(), dto.getName());
        Assertions.assertEquals(prescriptionDto.getDescription(), dto.getDescription());
        Assertions.assertEquals(visit, dto.getVisit());
    }
}