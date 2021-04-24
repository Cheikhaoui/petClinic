package springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springframework.model.Owner;
import springframework.services.OwnerService;

import java.util.List;


@Controller
@RequestMapping("/owners")
public class OwnerController {

    public static final String VIEW_OWNER_CREATE_UPDATE = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping
    public String findOwners(Owner owner , BindingResult bindingResult , Model model){
        String lastName = owner == null || owner.getLastName()==null || owner.getLastName().isEmpty()?"":owner.getLastName();
        List<Owner> owners =  ownerService.findAllByLastNameLike("%"+lastName+"%");
        if (CollectionUtils.isEmpty(owners)){
            bindingResult.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        }else if(owners.size() == 1){
            Long id = owners.get(0).getId();
            return "redirect:/owners/"+id;
        }else {
            model.addAttribute("selections",owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/find")
    public String find(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/new")
    public String newOwner(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return VIEW_OWNER_CREATE_UPDATE;
    }

    @PostMapping("/new")
    public String creatOwner(Owner owner,BindingResult result){
        if(result.hasErrors()){
            return VIEW_OWNER_CREATE_UPDATE;
        }else {
           Owner savedOwner =  ownerService.save(owner);
           return "redirect:/owners/"+savedOwner.getId();
        }
    }
    @GetMapping("/{ownerId}/edit")
    public String updateOwner(@PathVariable(name = "ownerId") Long id , Model model){
        Owner owner = ownerService.findById(id);
        model.addAttribute(owner);
        return VIEW_OWNER_CREATE_UPDATE;
    }

    @PostMapping("/{ownerId}/edit")
    public String updateOwner(Owner owner,BindingResult result,@PathVariable("ownerId") Long id){
        if(result.hasErrors()){
            return VIEW_OWNER_CREATE_UPDATE;
        }else {
            owner.setId(id);
           Owner savedOwner =  ownerService.save(owner);
           return "redirect:/owners/"+savedOwner.getId();
        }
    }
}
