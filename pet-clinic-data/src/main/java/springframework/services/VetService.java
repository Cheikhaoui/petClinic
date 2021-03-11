package springframework.services;

import springframework.model.Owner;
import springframework.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Owner owner);
    Set<Vet> findAll();
}
