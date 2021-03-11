package springframework.services;

import springframework.model.Owner;
import springframework.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Owner owner);
    Set<Pet> findAll();
}
