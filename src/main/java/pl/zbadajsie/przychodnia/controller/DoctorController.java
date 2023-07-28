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
    public String getAllPatient(Model model) {
        Optional<List<PatientInfoDto>> patientInfoDto = doctorService.getPatient();
        patientInfoDto.ifPresent(patientInfoDtos -> model.addAttribute("patients", patientInfoDtos));
        return "doctorPatient";
    }

    @GetMapping("patient/{userName}")
    public String getUserVisit(@PathVariable String userName, Model model) {
        Optional<List<VisitDoctorDto>> visits = doctorService.getUserVisit(userName);
        visits.ifPresent(visitDoctorDtos -> model.addAttribute("visits", visitDoctorDtos));
        return "visitDoctorPatient";
    }

    @GetMapping("visit/{id}")
    public String getInfoVisit(@PathVariable Long id, Model model) {
        if (!visitService.checkExist(id)) {
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego doktora ");
            return "visitPatient";
        }
        Optional<VisitDoctorDto> visitDoctorDto = doctorService.getVisitById(id);
        visitDoctorDto.ifPresent(visit -> model.addAttribute("visit", visit));
        if (visitService.visitWas(id)) {
            model.addAttribute("note", visitService.getNote(id));
            Optional<List<ReferralDto>> referralDto = visitService.getReferral(id);
            referralDto.ifPresent(referralDto2 -> model.addAttribute("referral", referralDto2));
            Optional<List<PrescriptionDto>> prescriptionDto = visitService.getPrescription(id);
            prescriptionDto.ifPresent(prescriptionDto2 -> model.addAttribute("prescription", prescriptionDto2));
        }
        return "visitDoctorInfo";
    }

    @GetMapping("visit/{id}/info")
    public String visitDoctorInfo(@PathVariable Long id, Model model) {
        if (!visitService.checkExist(id)) {
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego doktora ");
            return "visitDoctorForm";
        }
        Optional<VisitDoctorDto> visitDoctorDto = doctorService.getVisitById(id);
        visitDoctorDto.ifPresent(visit -> model.addAttribute("visit", visit));
        Optional<NoteDto> noteFromVisit = visitService.getNoteFromVisit(id);
        noteFromVisit.ifPresent(visitInfo -> model.addAttribute("noteInfo", visitInfo));
        return "visitDoctorForm";
    }

    @PostMapping("visit/{id}/info")
    public String visitDoctorInfo2(@PathVariable Long id, @Valid @ModelAttribute("noteInfo") NoteDto noteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            noteDto.setId(null);
            Optional<VisitDoctorDto> visitDoctorDto = doctorService.getVisitById(id);
            visitDoctorDto.ifPresent(visit -> model.addAttribute("visit", visit));
            return "visitDoctorForm";
        }
        visitService.addNote(noteDto, id);
        return "redirect:info";
    }

    @PostMapping("accept")
    public String accept(@ModelAttribute("visit") VisitDoctorDto dto) {
        visitService.accept(dto);
        int id = dto.getId();
        return "redirect:visit/" + id + "/info";
    }

    @GetMapping("/referral/{id}")
    public String addReferralForm(@PathVariable Long id,@RequestParam(name = "added",required = false)Boolean added,  Model model) {
        if (!visitService.checkExist(id)) {
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego doktora ");
            return "addReferral";
        }
        if(added!=null){
            model.addAttribute("added","Cos");
        }
        int id1 = id.intValue();
        ReferralDto build = ReferralDto.builder()
                .id(id1)
                .build();
        model.addAttribute("referral", build);
        return "addReferral";
    }

    @PostMapping("/referral/{id}")
    public String addReferral(@PathVariable Long id, @Valid @ModelAttribute("referral") ReferralDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addReferral";
        }

        visitService.addReferral(dto, id);
        return "redirect:" + id + "?added=true";
    }

    @GetMapping("/prescription/{id}")
    public String addPrescriptionForm(@PathVariable Long id,@RequestParam(name = "added",required = false)Boolean added, Model model) {
        if (!visitService.checkExist(id)) {
            model.addAttribute("notExist", "Wizyta o id: " + id + " nie istnieje dla tego doktora ");
            return "addPrescription";
        }
        if(added!=null){
            model.addAttribute("added","Cos");
        }
        int id1 = id.intValue();
        PrescriptionDto build = PrescriptionDto.builder()
                .id(id1)
                .build();
        model.addAttribute("prescription", build);
        return "addPrescription";
    }

    @PostMapping("/prescription/{id}")
    public String addPrescription(@PathVariable Long id, @Valid @ModelAttribute("prescription") PrescriptionDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPrescription";
        }
        visitService.addPrescription(dto, id);
        return "redirect:" + id + "?added=true";
    }

    @GetMapping("/info")
    public String getInfoDoctor(Model model){
        DoctorFullInfoDto doctorInfoDto = doctorService.getDoctorInfo();
        model.addAttribute("doctor", doctorInfoDto);
        return "aboutMeDoctor";
    }
}
