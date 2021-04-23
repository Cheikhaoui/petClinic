package springframework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springframework.model.Owner;
import springframework.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    Set<Owner> ownerset;

    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().id(1l).build();
        Owner owner1 = Owner.builder().id(2l).build();
        ownerset = new HashSet<>();
        ownerset.add(owner);
        ownerset.add(owner1);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOfOwners() throws Exception {
        Mockito.when(ownerService.findAll()).thenReturn(ownerset);
        mockMvc.perform(get("/owners/index")).andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwner() throws Exception {
        mockMvc.perform(get("/owners/find")).andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));
        Mockito.verifyNoMoreInteractions(ownerService);

    }

    @Test
    void displayOwner() throws Exception{
        Mockito.when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());
        mockMvc.perform(get("/owners/123")).andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner",hasProperty("id",is(1l))));
    }


}