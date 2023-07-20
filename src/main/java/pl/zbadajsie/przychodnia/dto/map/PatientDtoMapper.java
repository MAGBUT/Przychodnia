package pl.zbadajsie.przychodnia.dto.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.model.Address;
import pl.zbadajsie.przychodnia.model.Person;

@Service
public class PatientDtoMapper {

    public Person mapPerson(PatientRegistrationDto dto, Address address) {
        return Person.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .pesel(dto.getPesel())
                .address(address)
                .build();
    }

    public Address mapAddress(PatientRegistrationDto dto) {
        return Address.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .postalCode(dto.getPostalCode())
                .address(dto.getAddress())
                .build();
    }
}
