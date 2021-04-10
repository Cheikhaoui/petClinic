package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.model.Pet;
import springframework.model.Vet;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
