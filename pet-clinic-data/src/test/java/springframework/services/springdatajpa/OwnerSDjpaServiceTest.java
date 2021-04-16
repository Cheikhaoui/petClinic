package springframework.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import springframework.model.Owner;
import springframework.repositories.OwnerRepository;
import springframework.repositories.PetRepository;
import springframework.repositories.PetTypeRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDjpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDjpaService ownerSDjpaService;

    static final Long firstId = 1l;
    static final Long secondId = 2l;
    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(Owner.builder().id(firstId).build());
        assertEquals(firstId,ownerSDjpaService.findByLastName("hicham").getId());
    }

    @Test
    void findAll() {
        Owner first = Owner.builder().id(firstId).build();
        Owner second = Owner.builder().id(secondId).build();
        Set<Owner> owners = new HashSet<>();
        owners.add(first);
        owners.add(second);
        when(ownerRepository.findAll()).thenReturn(owners);
        assertEquals(ownerSDjpaService.findAll().size(),2l);

    }

    @Test
    void findById() {
        Owner first = Owner.builder().id(firstId).build();
        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(first));
        assertEquals(ownerSDjpaService.findById(anyLong()).getId(),firstId);
    }

    @Test
    void save() {
        Owner first = Owner.builder().id(firstId).build();
        when(ownerRepository.save(first)).thenReturn(first);
        assertEquals(ownerSDjpaService.save(first).getId(),firstId);
    }

    @Test
    void delete() {
        Owner first = Owner.builder().id(firstId).build();
        ownerSDjpaService.delete(first);
        verify(ownerRepository,times(1)).delete(first);
    }

    @Test
    void deleteById() {
        ownerSDjpaService.deleteById(firstId);
        verify(ownerRepository,times(1)).deleteById(firstId);
    }
}