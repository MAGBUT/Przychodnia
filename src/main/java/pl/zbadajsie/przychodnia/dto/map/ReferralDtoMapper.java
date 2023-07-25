package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.ReferralDto;
import pl.zbadajsie.przychodnia.model.Referral;

@Service
public class ReferralDtoMapper {
    public ReferralDto map(Referral referral){
        return ReferralDto.builder()
                .id(referral.getId())
                .title(referral.getTitle())
                .description(referral.getDescription())
                .build();
    }
}
