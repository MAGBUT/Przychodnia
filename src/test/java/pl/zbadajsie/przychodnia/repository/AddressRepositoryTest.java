package pl.zbadajsie.przychodnia.repository;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.zbadajsie.przychodnia.model.Address;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AddressRepositoryTest {

    private AddressRepository addressRepository;

    @Test
    void test() {
        Address address = getAddress();

        addressRepository.save(address);

        List<Address> all = (List<Address>) addressRepository.findAll();

        Assertions.assertEquals(all.get(0), address);
    }

    private Address getAddress() {
        return Address.builder()
                .address("Address")
                .country("Country")
                .city("City")
                .postalCode("PostalCode")
                .build();
    }
}