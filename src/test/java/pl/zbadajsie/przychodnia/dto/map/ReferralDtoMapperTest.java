package pl.zbadajsie.przychodnia.dto.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.ReferralDto;
import pl.zbadajsie.przychodnia.model.Referral;
import pl.zbadajsie.przychodnia.model.Visit;

@ExtendWith(MockitoExtension.class)
class ReferralDtoMapperTest {

    @InjectMocks
    private ReferralDtoMapper referralDtoMapper;

    @Mock
    private Referral referral;

    @Mock
    private ReferralDto referralDto;

    @Mock
    private Visit visit;

    @Test
    void mapReferral() {
        // Given
        Mockito.when(referral.getId()).thenReturn(1);
        Mockito.when(referral.getTitle()).thenReturn("Title");
        Mockito.when(referral.getDescription()).thenReturn("Description");

        // When
        ReferralDto dto = referralDtoMapper.map(referral);

        // Then
        Assertions.assertEquals(referral.getId(), dto.getId());
        Assertions.assertEquals(referral.getTitle(), dto.getTitle());
        Assertions.assertEquals(referral.getDescription(), dto.getDescription());
    }

    @Test
    void map() {
        // Given
        Mockito.when(referralDto.getTitle()).thenReturn("Title");
        Mockito.when(referralDto.getDescription()).thenReturn("Description");

        // When
        Referral referral = referralDtoMapper.map(referralDto, visit);

        // Then
        Assertions.assertEquals(referralDto.getTitle(), referral.getTitle());
        Assertions.assertEquals(referralDto.getDescription(), referral.getDescription());
        Assertions.assertEquals(visit, referral.getVisit());
    }
}