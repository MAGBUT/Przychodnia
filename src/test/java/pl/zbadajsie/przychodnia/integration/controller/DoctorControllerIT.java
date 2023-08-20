package pl.zbadajsie.przychodnia.integration.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.zbadajsie.przychodnia.controller.DoctorController;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.service.DoctorService;
import pl.zbadajsie.przychodnia.service.VisitService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(DoctorController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class DoctorControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private VisitService visitService;

    @MockBean
    private DoctorService doctorService;


    @Test
    @WithMockUser
    void getVisit() throws Exception {
        Optional<List<VisitDoctorDto>> visit = Optional.of(List.of(new VisitDoctorDto(), new VisitDoctorDto()));

        when(doctorService.getVisit()).thenReturn(visit);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/visit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("visitDoctor"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visits"));

    }

    @Test
    @WithMockUser
    void getAllPatient() throws Exception {
        Optional<List<PatientInfoDto>> visit = Optional.of(List.of(new PatientInfoDto(), new PatientInfoDto()));

        when(doctorService.getPatient()).thenReturn(visit);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/patient"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("doctorPatient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("patients"));

    }

    @Test
    @WithMockUser
    void getUserVisit() throws Exception {
        String userName = "userName";
        Optional<List<VisitDoctorDto>> visit = Optional.of(List.of(new VisitDoctorDto(), new VisitDoctorDto()));

        when(doctorService.getUserVisit(userName)).thenReturn(visit);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/patient/" + userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("visitDoctorPatient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visits"));

    }

    @Test
    @WithMockUser
    void getInfoVisitExistAndWas() throws Exception {
        Long id = 1L;
        Optional<VisitDoctorDto> visit = Optional.of(new VisitDoctorDto());

        when(doctorService.getVisitById(id)).thenReturn(visit);
        when(visitService.checkExist(id)).thenReturn(true);
        when(visitService.visitWas(id)).thenReturn(true);
        when(visitService.getNote(id)).thenReturn(new NoteDto());
        when(visitService.getReferral(id)).thenReturn(Optional.of(List.of(new ReferralDto(), new ReferralDto())));
        when(visitService.getPrescription(id)).thenReturn(Optional.of(List.of(new PrescriptionDto(), new PrescriptionDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/visit/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("visitDoctorInfo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("note"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("referral"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("prescription"));

    }

    @Test
    @WithMockUser
    void getInfoVisit() throws Exception {
        Long id = 1L;
        Optional<VisitDoctorDto> visit = Optional.of(new VisitDoctorDto());

        when(visitService.checkExist(id)).thenReturn(false);


        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/visit/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("visitPatient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("notExist"));

    }

    @Test
    @WithMockUser
    void visitDoctorInfo() throws Exception {
        Long id = 1L;
        Optional<VisitDoctorDto> visit = Optional.of(new VisitDoctorDto());

        when(visitService.checkExist(id)).thenReturn(true);
        when(doctorService.getVisitById(id)).thenReturn(visit);
        when(visitService.getNoteFromVisit(id)).thenReturn(Optional.of(new NoteDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/visit/" + id + "/info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("visitDoctorForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noteInfo"));

    }

    @Test
    @WithMockUser
    void visitDoctorInfoError() throws Exception {
        Long id = 1L;

        when(visitService.checkExist(id)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/visit/" + id + "/info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("visitDoctorForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("notExist"));

    }

    @Test
    @WithMockUser
    void visitDoctorInfo2() throws Exception {
        Long id = 1L;
        NoteDto noteDto = NoteDto.builder()
                .title("title")
                .description("descriptiondescriptiondescription")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor/visit/" + id + "/info")
                        .flashAttr("noteInfo", noteDto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("info"));

    }

    @Test
    @WithMockUser
    void visitDoctorInfoWrong() throws Exception {
        Long id = 1L;
        NoteDto noteDto  = NoteDto.builder()
                .title("t")
                .description("d")
                .build();
        when(doctorService.getVisitById(id)).thenReturn(Optional.of(new VisitDoctorDto()));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor/visit/" + id + "/info")
                        .flashAttr("noteInfo", noteDto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.view().name("visitDoctorForm"));

    }

    @Test
    @WithMockUser
    void accept() throws Exception {
        VisitDoctorDto dto = VisitDoctorDto.builder().id(1).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor/accept")
                        .flashAttr("visit", dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("visit/1/info"));

    }

    @Test
    @WithMockUser
    void addReferralForm() throws Exception {
        Long id = 1L;
        VisitDoctorDto dto = VisitDoctorDto.builder().id(1).build();

        when(visitService.checkExist(id)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/doctor/referral/"+ id)
                        .param("added","true")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("added"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("referral"))
                .andExpect(MockMvcResultMatchers.view().name("addReferral"));

    }


}
