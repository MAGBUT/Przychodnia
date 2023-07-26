package pl.zbadajsie.przychodnia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
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
}
