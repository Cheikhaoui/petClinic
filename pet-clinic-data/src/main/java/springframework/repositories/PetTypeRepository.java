package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.model.Pet;
import springframework.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
