package springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springframework.model.Owner;
import springframework.services.OwnerService;

import java.util.List;


@Controller
@RequestMapping("/owners")
public class OwnerController {

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
        List<Owner> owners =  ownerService.findAllByLastNameLike("%"+(owner.getLastName().isEmpty()?"":owner.getLastName())+"%");
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
}
