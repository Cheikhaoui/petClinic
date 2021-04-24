package springframework.controllers;

import jdk.nashorn.internal.ir.annotations.Ignore;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void displayOwner() throws Exception{
        Mockito.when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());
        mockMvc.perform(get("/owners/123")).andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner",hasProperty("id",is(1l))));
    }

    @Test
    void findOneResultFromFind() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).
                thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                        Owner.builder().id(2l).build()));
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections",hasSize(2)));
    }
    @Test
    void findMultipleResultFromFind() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).
                thenReturn(Collections.singletonList(Owner.builder().id(1l).build()));
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void newOwnerTest() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(OwnerController.VIEW_OWNER_CREATE_UPDATE));
    }

    @Test
    void creatOwnerTest() throws Exception {
        when(ownerService.save(any())).
                thenReturn(Owner.builder().id(1l).build());
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

}