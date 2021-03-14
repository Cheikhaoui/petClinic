package springframework.services;

import springframework.model.Owner;


public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
}
