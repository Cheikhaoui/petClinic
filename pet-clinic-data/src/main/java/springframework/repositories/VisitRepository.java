package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.model.Pet;
import springframework.model.Visit;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
