package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.model.Pet;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
