package pl.zbadajsie.przychodnia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.service.DoctorService;
import pl.zbadajsie.przychodnia.service.VisitService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private final VisitService visitService;
    private final DoctorService doctorService;

    @GetMapping("/visit")
    public String getVisit(Model model) {
        Optional<List<VisitDoctorDto>> visit = doctorService.getVisit();
        visit.ifPresent(visitDoctorDtos -> model.addAttribute("visits", visitDoctorDtos));
        return "visitDoctor";
    }

    @GetMapping("patient")
    public String getAllPatient(Model model){
        Optional<List<PatientInfoDto>> patientInfoDto = doctorService.getPatient();
        patientInfoDto.ifPresent(patientInfoDtos -> model.addAttribute("patients",patientInfoDtos));
        return "doctorPatient";
    }

    @GetMapping("patient/{userName}")
    public String getUserVisit(@PathVariable String userName,Model model){
        Optional<List<VisitDoctorDto>> visits = doctorService.getUserVisit(userName);
        visits.ifPresent(visitDoctorDtos -> model.addAttribute("visits",visitDoctorDtos));
        return "visitDoctorPatient";
    }

    @GetMapping("visit/{id}")
    public String getInfoVisit(@PathVariable Long id, Model model){
        if(!visitService.checkExist(id)){
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego doktora ");
            return "visitPatient";
        }
        Optional<VisitDoctorDto> visitDoctorDto = doctorService.getVisitById(id);
        visitDoctorDto.ifPresent(visit->model.addAttribute("visit",visit));
        if(visitService.visitWas(id)){
            model.addAttribute("note" , visitService.getNote(id));
            Optional<List<ReferralDto>> referralDto = visitService.getReferral(id);
            referralDto.ifPresent(referralDto2 -> model.addAttribute("referral",referralDto2));
            Optional<List<PrescriptionDto>> prescriptionDto = visitService.getPrescription(id);
            prescriptionDto.ifPresent(prescriptionDto2 -> model.addAttribute("prescription",prescriptionDto2));
        }
        return "visitDoctorInfo";
    }

    @GetMapping("visit/{id}/info")
    public String visitDoctorAccept(@PathVariable Long id,Model model){
        if(!visitService.checkExist(id)){
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego doktora ");
            return "visitDoctorForm";
        }
        Optional<VisitDoctorDto> visitDoctorDto = doctorService.getVisitById(id);
        visitDoctorDto.ifPresent(visit->model.addAttribute("visit",visit));
        Optional<InfoFromVisitDto> infoFromVisitDto = visitService.getInfoFromVisit(id);
        infoFromVisitDto.ifPresent(visitInfo->model.addAttribute("visitInfo",visitInfo));
        return "visitDoctorForm";
    }

    @PostMapping("accept")
    public String accept(@ModelAttribute("visit") VisitDoctorDto dto){
        boolean accept = dto.isAccept();
        visitService.accept(dto);
        int id = dto.getId();
        return "redirect:visit/"+id+"/info";
    }
}
