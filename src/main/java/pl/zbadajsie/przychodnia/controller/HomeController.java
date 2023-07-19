package pl.zbadajsie.przychodnia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.service.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SecurityContextFacade securityContextFacade;
    private final UserService userServise;

    static final String HOME = "/";

    @GetMapping(HOME)
    public String homePage(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("wrongUsernameOrPassword", "Błęde hasło lub login!");
        }
        return "login";
    }

    @GetMapping("/homepage")
    public String homePageLogin(){
        User loggedInUser = securityContextFacade.getLoggedInUser();
        if(loggedInUser != null){
            String role = userServise.getRole(loggedInUser.getUsername());
            if(role.equals("DOCTOR")){
                return "redirect:doctor/homepage";
            }else if(role.equals("PATIENT")){
                return "redirect:patient/homepage";
            }
        }
        return "login";
    }

    @GetMapping("patient/homepage")
    public String homePagePatient(){
        return "homePageForPatient";
    }

    @GetMapping("doctor/homepage")
    public String homePageDoctor(){
        return "homePageForDoctor";
    }
}
