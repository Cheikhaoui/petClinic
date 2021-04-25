package springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springframework.model.Pet;
import springframework.model.Visit;
import springframework.services.PetService;
import springframework.services.VisitService;

@Controller
public class VisitController {
    private static  final String CREATE_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId , Model model){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet",pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initVisit(){
        return CREATE_UPDATE_VISIT_FORM;
    }

    @PostMapping("/owners/*/pets/{petId}/visits/new")
    public String processVisit(Visit visit, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        return CREATE_UPDATE_VISIT_FORM;
        else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }


}
