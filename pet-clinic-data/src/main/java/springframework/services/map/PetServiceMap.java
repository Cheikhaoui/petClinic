package springframework.services.map;

import springframework.model.Pet;
import springframework.services.CrudService;

import javax.persistence.Id;
import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements CrudService<Pet,Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
            super.deleteById(id);
    }

    @Override
    public void delete(Pet t) {
        super.delete(t);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(),pet);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
