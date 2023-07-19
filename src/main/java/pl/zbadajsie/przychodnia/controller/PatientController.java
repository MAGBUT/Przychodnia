package pl.zbadajsie.przychodnia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.service.PatientService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;


    @GetMapping("/doctor")
    public String allDoctors(Model model){
        List<DoctorInfoDto> doctorInfoDtoList = patientService.getAllDoctors();
        model.addAttribute("doctors",doctorInfoDtoList);
        return "availableDoctor";
    }

}
