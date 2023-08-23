package pl.zbadajsie.przychodnia.unite.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;
import pl.zbadajsie.przychodnia.controller.PatientController;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.service.PatientService;
import pl.zbadajsie.przychodnia.service.VisitService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;
    @Mock
    private PatientService patientService;
    @Mock
    private VisitService visitService;


    @Test
    void aboutMe() {
        ExtendedModelMap model = new ExtendedModelMap();
        Optional<List<VisitDto>> visit = Optional.of(List.of(new VisitDto()));
        when(patientService.getPatientInfo()).thenReturn(new PatientInfoDto());
        when(visitService.getVisit()).thenReturn(visit);


        String result = patientController.aboutMe(model);

        Assertions.assertEquals(result, "aboutMePatient");
        Assertions.assertEquals(visit.get(), model.getAttribute("visits"));
        Assertions.assertEquals(new PatientInfoDto(), model.getAttribute("person"));
    }

    @Test
    void allDoctors() {
        ExtendedModelMap model = new ExtendedModelMap();
        List<DoctorInfoDto> doctor = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctor);

        String result = patientController.allDoctors(model);

        Assertions.assertEquals(result, "availableDoctor");
        Assertions.assertEquals(doctor, model.getAttribute("doctors"));
    }

    @Test
    void bookVisitForm() {
        ExtendedModelMap model = new ExtendedModelMap();
        List<DoctorInfoDto> doctorInfoDtoList = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctorInfoDtoList);

        String result = patientController.bookVisitForm("yes", model);

        Assertions.assertEquals(result, "bookVisit");
        Assertions.assertEquals(doctorInfoDtoList, model.getAttribute("allDoctors"));
        Assertions.assertEquals("Dodano wizytę", model.getAttribute("success"));
    }

    @Test
    void bookVisitHasBinding() {
        ExtendedModelMap model = new ExtendedModelMap();
        VisitDto visit = new VisitDto();
        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(true);
        List<DoctorInfoDto> doctorInfoDtoList = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctorInfoDtoList);

        String result = patientController.bookVisit(visit, binding, model);

        Assertions.assertEquals(result, "bookVisit");
        Assertions.assertEquals(doctorInfoDtoList, model.getAttribute("allDoctors"));
    }

    @Test
    void bookVisitDateIsNotFree() {
        ExtendedModelMap model = new ExtendedModelMap();
        VisitDto visit = new VisitDto();
        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(false);
        List<DoctorInfoDto> doctorInfoDtoList = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctorInfoDtoList);
        when(visitService.freeDateTme(visit)).thenReturn(false);

        String result = patientController.bookVisit(visit, binding, model);

        Assertions.assertEquals(result, "bookVisit");
        Assertions.assertEquals(doctorInfoDtoList, model.getAttribute("allDoctors"));
        Assertions.assertEquals("Ten termin jest juz zajety", model.getAttribute("wrongDate"));
    }

    @Test
    void bookVisitDate() {
        ExtendedModelMap model = new ExtendedModelMap();
        VisitDto visit = new VisitDto();
        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(false);
        when(visitService.freeDateTme(visit)).thenReturn(true);

        String result = patientController.bookVisit(visit, binding, model);

        Assertions.assertEquals(result, "redirect:/patient/visit?param=yes");
    }

    @Test
    void getVisitInfoNotExist() {
        ExtendedModelMap model = new ExtendedModelMap();
        Long id = 1L;
        when(visitService.checkExist(id)).thenReturn(false);

        String result = patientController.getInfoVisit(id, model);

        Assertions.assertEquals(result, "visitPatient");
        Assertions.assertEquals("Wizyta o id: " + id + " nie istnieje dla tego pacjenta", model.getAttribute("notExist"));
    }

    @Test
    void getVisitInfo() {
        ExtendedModelMap model = new ExtendedModelMap();
        Long id = 1L;
        when(visitService.checkExist(id)).thenReturn(true);
        DoctorInfoDto doctorInfoDto = new DoctorInfoDto();
        Optional<VisitDto> visit = Optional.of(new VisitDto());
        when(visitService.getDoctor(id)).thenReturn(doctorInfoDto);
        when(visitService.getVisit(id)).thenReturn(visit);

        String result = patientController.getInfoVisit(id, model);

        Assertions.assertEquals(result, "visitPatient");
        Assertions.assertEquals(visit.get(), model.getAttribute("visit"));
        Assertions.assertEquals(doctorInfoDto, model.getAttribute("doctor"));
    }

    @Test
    void getVisitInfoVisitWas() {
        ExtendedModelMap model = new ExtendedModelMap();
        Long id = 1L;
        when(visitService.checkExist(id)).thenReturn(true);
        DoctorInfoDto doctorInfoDto = new DoctorInfoDto();
        Optional<VisitDto> visit = Optional.of(new VisitDto());
        when(visitService.getDoctor(id)).thenReturn(doctorInfoDto);
        when(visitService.getVisit(id)).thenReturn(visit);
        when(visitService.visitWas(id)).thenReturn(true);
        NoteDto note = new NoteDto();
        when(visitService.getNote(id)).thenReturn(note);
        Optional<List<ReferralDto>> referral = Optional.of(List.of(new ReferralDto()));
        Optional<List<PrescriptionDto>> prescription = Optional.of(List.of(new PrescriptionDto()));
        when(visitService.getReferral(id)).thenReturn(referral);
        when(visitService.getPrescription(id)).thenReturn(prescription);


        String result = patientController.getInfoVisit(id, model);

        Assertions.assertEquals(result, "visitPatient");
        Assertions.assertEquals(visit.get(), model.getAttribute("visit"));
        Assertions.assertEquals(doctorInfoDto, model.getAttribute("doctor"));
        Assertions.assertEquals(note, model.getAttribute("note"));
        Assertions.assertEquals(referral.get(), model.getAttribute("referral"));
        Assertions.assertEquals(prescription.get(), model.getAttribute("prescription"));
    }
}