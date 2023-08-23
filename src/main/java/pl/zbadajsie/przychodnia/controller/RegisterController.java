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
    public String register() {
        return "register";
    }

    @GetMapping("/registerForPatient")
    public String registerForPatient(Model model) {
        model.addAttribute("user", new PatientRegistrationDto());
        return "registerForPatient";
    }

    @GetMapping("/registerForDoctor")
    public String registerForDoctor(Model model) {
        DoctorRegistrationDto doctorDto = new DoctorRegistrationDto();
        doctorDto.setSpecialization(doctorRegisterService.getAllSpecializations());
        model.addAttribute("user", doctorDto);
        return "registerForDoctor";
    }


    @PostMapping("/registerForPatient")
    public String registerForPatient(@Valid @ModelAttribute("user") PatientRegistrationDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerForPatient";
        }
        boolean emailIsTake = patientRegisterService.checkEmail(dto.getEmail());
        boolean userNameIsTake = patientRegisterService.checkUserName(dto.getUserName());
        if (userNameIsTake || emailIsTake) {
            if(userNameIsTake)
                model.addAttribute("wrongUsername", "Użytkownik o takiej nazwie już isnieje");
            if(emailIsTake)
                model.addAttribute("wrongEmail", "Użytkownik o takim emailu juz isnieje");
            return "registerForPatient";
        }
        patientRegisterService.register(dto);
        return "redirect:/success";
    }

    @PostMapping("/registerForDoctor")
    String registerForDoctor(@Valid @ModelAttribute("user") DoctorRegistrationDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerForDoctor";
        }
        boolean emailIsTake = doctorRegisterService.checkEmail(dto.getEmail());
        boolean userNameIsTake = doctorRegisterService.checkUserName(dto.getUserName());
        if (userNameIsTake || emailIsTake) {
            if(userNameIsTake)
              model.addAttribute("wrongUsername", "Użytkownik o takiej nazwie już isnieje");
            if(emailIsTake)
                model.addAttribute("wrongEmail", "Użytkownik o takim emailu juz isnieje");
            return "registerForDoctor";
        }
        doctorRegisterService.register(dto);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "registerSuccess";
    }
}
