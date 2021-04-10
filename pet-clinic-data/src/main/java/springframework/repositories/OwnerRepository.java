package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
}
