package springframework.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springframework.model.Owner;
import springframework.model.Pet;
import springframework.services.OwnerService;
import springframework.services.PetService;
import springframework.services.PetTypeService;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
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
        Optional<Map.Entry<Long,Owner>>  any =  map.entrySet().stream().filter(e->
            e.getValue().getLastName().equalsIgnoreCase(lastName)
        ).findAny();
        return any.map(Map.Entry::getValue).orElse(null);
    }
}
