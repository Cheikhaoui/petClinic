package springframework.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class IndexController {

    @RequestMapping({"","/","index.html","index"})
    public String getIndexPage(Model model){
        return "vets/index";
    }
}
