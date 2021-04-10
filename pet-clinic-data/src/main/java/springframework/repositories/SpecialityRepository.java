package springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.model.Pet;
import springframework.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
}
