package springframework.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springframework.model.Vet;
import springframework.services.VetService;

import java.util.Set;

@Controller
@RequestMapping("")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets","/vets/","/vets/index.html","/vets/index","/vets.html"})
    public String listVets(Model model)
    {
        model.addAttribute("vets",vetService.findAll());
        return "vets/index";
    }

    @GetMapping({"/api/vets"})
    public @ResponseBody
    Set<Vet> getVetsJson()
    {
        return  vetService.findAll();
    }
}
