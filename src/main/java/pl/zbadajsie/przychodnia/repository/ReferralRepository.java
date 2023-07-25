package pl.zbadajsie.przychodnia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zbadajsie.przychodnia.model.Referral;

@Repository
public interface ReferralRepository extends CrudRepository<Referral,Long> {
}
