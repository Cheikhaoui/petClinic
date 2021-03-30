package springframework.services.map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springframework.model.Owner;
import springframework.model.Pet;
import springframework.model.PetType;
import springframework.services.OwnerService;
import springframework.services.PetService;
import springframework.services.PetTypeService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner != null ) {
            if(!CollectionUtils.isEmpty(owner.getPets())){
                owner.getPets().forEach(p->{
                    if(p.getPetType() != null && p.getPetType().getId() == null){
                        p.setPetType(petTypeService.save(p.getPetType()));
                    }
                    if(p.getId() == null){
                        Pet pet = petService.save(p);
                        p.setId(pet.getId());
                    }
                });
            }
            return super.save(owner);
        }else {
            return null;
        }
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
