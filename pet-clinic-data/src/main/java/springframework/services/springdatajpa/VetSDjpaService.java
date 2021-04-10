package springframework.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import springframework.model.Owner;
import springframework.model.Vet;
import springframework.repositories.OwnerRepository;
import springframework.repositories.PetRepository;
import springframework.repositories.PetTypeRepository;
import springframework.repositories.VetRepository;
import springframework.services.OwnerService;
import springframework.services.VetService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDjpaService implements VetService {

    private final VetRepository vetRepository;


    public VetSDjpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.findById(id);
    }
}
