package pl.zbadajsie.przychodnia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    static final String HOME = "/";

    @GetMapping(HOME)
    public String homePage(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/homepage")
    public String homePageLogin(){
        return "homepage";
    }

}
