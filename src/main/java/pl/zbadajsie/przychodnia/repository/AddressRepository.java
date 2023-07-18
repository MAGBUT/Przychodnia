package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

}
