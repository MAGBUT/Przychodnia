package pl.zbadajsie.przychodnia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.service.DoctorRegisterService;
import pl.zbadajsie.przychodnia.service.PatientRegisterService;

@RequiredArgsConstructor
@Controller
public class RegisterController {

    private final PatientRegisterService patientRegisterService;
    private final DoctorRegisterService doctorRegisterService;

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @GetMapping("/registerForPatient")
    String registerForPatient (Model model){
        model.addAttribute("user", new PatientRegistrationDto());
        return "registerForPatient";
    }

    @GetMapping("/registerForDoctor")
    String registerForDoctor (Model model){
        DoctorRegistrationDto doctorDto = new DoctorRegistrationDto();
        doctorDto.setSpecialization(doctorRegisterService.getAllSpecializations());
        model.addAttribute("user", doctorDto);
        return "registerForDoctor";
    }


    @PostMapping("/registerForPatient")
    String registerForPatient(@Valid @ModelAttribute("user") PatientRegistrationDto dto, BindingResult bindingResult, Model model) {
        boolean userNameIsTake = doctorRegisterService.checkUserName(dto.getUserName());
        if (bindingResult.hasErrors() || userNameIsTake) {
            if(userNameIsTake){
                model.addAttribute("wrongUsername", "Użytkownik o takiej nazwie już isnieje");
            }
            return "registerForPatient";
        } else {
            patientRegisterService.register(dto);
            return "redirect:success";
        }
    }

    @PostMapping("/registerForDoctor")
    String registerForDoctor(@Valid @ModelAttribute("user") DoctorRegistrationDto dto, BindingResult bindingResult, Model model) {
        boolean userNameIsTake = doctorRegisterService.checkUserName(dto.getUserName());
        if (bindingResult.hasErrors() || userNameIsTake) {
            dto.setSpecialization(doctorRegisterService.getAllSpecializations());
            model.addAttribute("user", dto);
            if(userNameIsTake){
                model.addAttribute("wrongUsername", "Użytkownik o takiej nazwie już isnieje");
            }
            return "registerForDoctor";
        } else {
            doctorRegisterService.register(dto);
            return "redirect:success";
        }
    }

    @GetMapping("/success")
    String success() {
        return "registerSuccess";
    }
}
