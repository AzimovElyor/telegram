package uz.pdp.telegram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/telegram")
public class Home {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
