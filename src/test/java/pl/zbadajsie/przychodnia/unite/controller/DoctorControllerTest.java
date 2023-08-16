package pl.zbadajsie.przychodnia.unite.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import pl.zbadajsie.przychodnia.controller.DoctorController;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.service.DoctorService;
import pl.zbadajsie.przychodnia.service.VisitService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorService doctorService;

    @Mock
    private VisitService visitService;



    @Test
     void getVisitTest(){
        Optional<List<VisitDoctorDto>> visit = Optional.of(List.of(new VisitDoctorDto(),new VisitDoctorDto()));
        ExtendedModelMap model = new ExtendedModelMap();

        when(doctorService.getVisit()).thenReturn(visit);

        String result = doctorController.getVisit(model);

        Assertions.assertEquals(result,"visitDoctor");
        Assertions.assertEquals(visit.get(),model.getAttribute("visits"));
    }

    @Test
    void getAllPatientTest(){
        ExtendedModelMap model = new ExtendedModelMap();
        Optional<List<PatientInfoDto>> patientInfoDto = Optional.of(List.of(new PatientInfoDto()));

        when(doctorService.getPatient()).thenReturn(patientInfoDto);

        String result = doctorController.getAllPatient(model);

        Assertions.assertEquals(result,"doctorPatient");
        Assertions.assertEquals(patientInfoDto.get(),model.getAttribute("patients"));
    }

    @Test
    void getUserVisitTest(){
        ExtendedModelMap model = new ExtendedModelMap();
        Optional<List<VisitDoctorDto>> info = Optional.of(List.of(new VisitDoctorDto()));
        String userName = "Jan";

        when(doctorService.getUserVisit(Mockito.anyString())).thenReturn(info);

        String result = doctorController.getUserVisit(userName,model);

        Assertions.assertEquals(result,"visitDoctorPatient");
        Assertions.assertEquals(info.get(),model.getAttribute("visits"));
    }

    @Test
    void getInfoVisitExistAndWasTest(){
        ExtendedModelMap model = new ExtendedModelMap();
        Optional<VisitDoctorDto> visitDoctorDto = Optional.of(new VisitDoctorDto());
        Long id = 1L;

        when(visitService.checkExist(id)).thenReturn(true);
        when(doctorService.getVisitById(id)).thenReturn(visitDoctorDto);
        when(visitService.visitWas(id)).thenReturn(true);
        NoteDto note = new NoteDto();
        when(visitService.getNote(id)).thenReturn(note);
        Optional<List<ReferralDto>> referralDto = Optional.of(List.of(new ReferralDto()));
        when(visitService.getReferral(id)).thenReturn(referralDto);
        Optional<List<PrescriptionDto>> prescriptionDto = Optional.of(List.of(new PrescriptionDto()));
        when(visitService.getPrescription(id)).thenReturn(prescriptionDto);


        String result = doctorController.getInfoVisit(id,model);

        Assertions.assertEquals(result,"visitDoctorInfo");
        Assertions.assertEquals(model.getAttribute("note"),note);
        Assertions.assertEquals(model.getAttribute("referral"),referralDto.get());
        Assertions.assertEquals(model.getAttribute("prescription"),prescriptionDto.get());
        Assertions.assertEquals(model.getAttribute("visit"),visitDoctorDto.get());

    }

    @Test
    void getInfoVisitExistTest(){
        ExtendedModelMap model = new ExtendedModelMap();
        Optional<VisitDoctorDto> visitDoctorDto = Optional.of(new VisitDoctorDto());
        Long id = 1L;

        when(visitService.checkExist(id)).thenReturn(true);
        when(doctorService.getVisitById(id)).thenReturn(visitDoctorDto);
        when(visitService.visitWas(id)).thenReturn(false);


        String result = doctorController.getInfoVisit(id,model);

        Assertions.assertEquals(result,"visitDoctorInfo");
        assertNull(model.getAttribute("note"));
        assertNull(model.getAttribute("referral"));
        assertNull(model.getAttribute("prescription"));
        Assertions.assertEquals(model.getAttribute("visit"),visitDoctorDto.get());

    }

    @Test
    void getInfoVisitNotExist(){
        ExtendedModelMap model = new ExtendedModelMap();
        Long id = 1L;

        when(visitService.checkExist(id)).thenReturn(false);


        String result = doctorController.getInfoVisit(id,model);

        Assertions.assertEquals(model.getAttribute("notExist"),"Wizyta o id: " + id + " nie istnieje dla tego doktora ");
        Assertions.assertEquals(result,"visitPatient");
        assertNull(model.getAttribute("note"));
        assertNull(model.getAttribute("referral"));
        assertNull(model.getAttribute("prescription"));
        assertNull(model.getAttribute("visit"));

    }

    @Test
    void visitDoctorInfoTest(){
        ExtendedModelMap model = new ExtendedModelMap();
        Long id = 1L;

        when(visitService.checkExist(id)).thenReturn(false);

        String result = doctorController.visitDoctorInfo(id, model);

        Assertions.assertEquals(model.getAttribute("notExist"),"Wizyta o id: " + id + " nie istnieje dla tego doktora ");
        Assertions.assertEquals(result,"visitDoctorForm");
    }

}