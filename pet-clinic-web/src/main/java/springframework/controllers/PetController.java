package springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springframework.model.Owner;
import springframework.model.Pet;
import springframework.model.PetType;
import springframework.services.OwnerService;
import springframework.services.PetService;
import springframework.services.PetTypeService;

import javax.jws.WebParam;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final  String VIEW_PET_CREATE_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetType(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String createPet(@ModelAttribute Owner owner , Model model){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet",pet);
        return VIEW_PET_CREATE_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String createPet(Owner owner , Pet pet , BindingResult bindingResult ,Model model){
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName()) != null){
            bindingResult.rejectValue("name","duplicate","already exists");
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if(bindingResult.hasErrors()){
            model.addAttribute("pet",pet);
            return VIEW_PET_CREATE_UPDATE_FORM;
        }else {
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdatForm(@PathVariable Long petId,Model model){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet",pet);
        return VIEW_PET_CREATE_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Pet pet , Owner owner , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet",pet);
            return VIEW_PET_CREATE_UPDATE_FORM;
        }else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }



}
