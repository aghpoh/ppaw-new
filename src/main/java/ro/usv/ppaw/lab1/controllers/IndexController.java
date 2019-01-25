package ro.usv.ppaw.lab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class IndexController {
    @GetMapping
    public String index() {
        return "redirect:/rents/all";
    }
}
