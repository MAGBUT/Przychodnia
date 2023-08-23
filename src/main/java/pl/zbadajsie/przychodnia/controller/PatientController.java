package pl.zbadajsie.przychodnia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.service.PatientService;
import pl.zbadajsie.przychodnia.service.UserService;
import pl.zbadajsie.przychodnia.service.VisitService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final VisitService visitService;


    @GetMapping("/about")
    public String aboutMe(Model model){
        PatientInfoDto dto = patientService.getPatientInfo();
        Optional<List<VisitDto>> visit = visitService.getVisit();
        model.addAttribute("person",dto);
        visit.ifPresent(visitDto -> model.addAttribute("visits", visitDto));
        return "aboutMePatient";
    }


    @GetMapping("/doctor")
    public String allDoctors(Model model) {
        List<DoctorInfoDto> doctorInfoDtoList = patientService.getAllDoctors();
        model.addAttribute("doctors", doctorInfoDtoList);
        return "availableDoctor";
    }

    @GetMapping("/visit")
    public String bookVisitForm(@RequestParam(value = "param", required = false) String param, Model model) {
        List<DoctorInfoDto> doctorInfoDtoList = patientService.getAllDoctors();
        model.addAttribute("allDoctors", doctorInfoDtoList);
        model.addAttribute("visit", new VisitDto());
        if (param != null) {
            model.addAttribute("success", "Dodano wizytę");
        }
        return "bookVisit";
    }

    @PostMapping("/visit")
    public String bookVisit(@Valid @ModelAttribute("visit") VisitDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<DoctorInfoDto> doctorInfoDtoList = patientService.getAllDoctors();
            model.addAttribute("allDoctors", doctorInfoDtoList);
            return "bookVisit";
        }
        if (!visitService.freeDateTme(dto)) {
            model.addAttribute("wrongDate", "Ten termin jest juz zajety");
            List<DoctorInfoDto> doctorInfoDtoList = patientService.getAllDoctors();
            model.addAttribute("allDoctors", doctorInfoDtoList);
            return "bookVisit";
        }
        visitService.bookVisit(dto);
        return "redirect:/patient/visit?param=yes";

    }

    @GetMapping("visit/{id}")
    public String getInfoVisit(@PathVariable Long id, Model model){
        if(!visitService.checkExist(id)){
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego pacjenta");
            return "visitPatient";
        }
        DoctorInfoDto doctorInfoDto = visitService.getDoctor(id);
        model.addAttribute("doctor",doctorInfoDto);
        Optional<VisitDto> visit = visitService.getVisit(id);
        visit.ifPresent(visitDto -> model.addAttribute("visit",visitDto));
        if(visitService.visitWas(id)){
            model.addAttribute("note" , visitService.getNote(id));
            Optional<List<ReferralDto>> referralDto = visitService.getReferral(id);
            referralDto.ifPresent(referralDto2 -> model.addAttribute("referral",referralDto2));
            Optional<List<PrescriptionDto>> prescriptionDto = visitService.getPrescription(id);
            prescriptionDto.ifPresent(prescriptionDto2 -> model.addAttribute("prescription",prescriptionDto2));
        }
        return "visitPatient";
    }

}
