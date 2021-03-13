package springframework.services.map;

import springframework.model.Owner;
import springframework.services.CrudService;

import javax.persistence.Id;
import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner,Long>  implements CrudService<Owner,Long> {

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
        return super.save(owner.getId(),owner);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
