package springframework.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private  OwnerServiceMap ownerServiceMap;
    final  Long id = 1l;
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetServiceMap(),new PetTypeServiceMap());
        ownerServiceMap.save(Owner.builder().id(id).lastName("hicham").build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(id,ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(id);
        assertEquals(owner.getId(),id);
    }

    @Test
    void save() {
        Owner owner = ownerServiceMap.save(Owner.builder().id(2l).build());
        assertEquals(ownerServiceMap.findById(2l).getId(),owner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner ownerFinded = ownerServiceMap.findByLastName("hicham");
        assertEquals(ownerFinded.getId(),id);
    }
}