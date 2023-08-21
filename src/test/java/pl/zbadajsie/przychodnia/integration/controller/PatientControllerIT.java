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
import pl.zbadajsie.przychodnia.controller.PatientController;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;
import pl.zbadajsie.przychodnia.dto.VisitDto;
import pl.zbadajsie.przychodnia.service.PatientService;
import pl.zbadajsie.przychodnia.service.VisitService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(PatientController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PatientControllerIT {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PatientService patientService;
    @MockBean
    private VisitService visitService;

    @Test
    @WithMockUser
    void aboutMe() throws Exception {
        Optional<List<VisitDto>> visit = Optional.of(List.of(new VisitDto()));
        when(patientService.getPatientInfo()).thenReturn(new PatientInfoDto());
        when(visitService.getVisit()).thenReturn(visit);


        mockMvc.perform(MockMvcRequestBuilders.get("/patient/about"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visits"))
                .andExpect(MockMvcResultMatchers.view().name("aboutMePatient"));
    }

    @Test
    @WithMockUser
    void allDoctors() throws Exception {
        List<DoctorInfoDto> doctor = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctor);


        mockMvc.perform(MockMvcRequestBuilders.get("/patient/doctor"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors"))
                .andExpect(MockMvcResultMatchers.view().name("availableDoctor"));
    }

    @Test
    @WithMockUser
    void bookVisitForm() throws Exception {
        List<DoctorInfoDto> doctorInfoDtoList = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctorInfoDtoList);
        ;


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/visit")
                        .param("param", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("allDoctors"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("success"))
                .andExpect(MockMvcResultMatchers.view().name("bookVisit"));
    }

    @Test
    @WithMockUser
    void bookVisitBindingResult() throws Exception {
        VisitDto visit = VisitDto.builder().time(LocalTime.now()).date(LocalDate.now()).description("D").build();

        List<DoctorInfoDto> doctorInfoDtoList = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctorInfoDtoList);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/patient/visit")
                        .flashAttr("visit", visit)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("allDoctors"))
                .andExpect(MockMvcResultMatchers.view().name("bookVisit"));
    }

    @Test
    @WithMockUser
    void bookVisitDateIsNotFree() throws Exception {
        VisitDto visit = VisitDto.builder().time(LocalTime.now()).date(LocalDate.of(2023, 12, 12)).description("Description,Description").build();

        List<DoctorInfoDto> doctorInfoDtoList = List.of(new DoctorInfoDto());
        when(patientService.getAllDoctors()).thenReturn(doctorInfoDtoList);
        when(visitService.freeDateTme(visit)).thenReturn(false);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/patient/visit")
                        .flashAttr("visit", visit)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("wrongDate"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allDoctors"))
                .andExpect(MockMvcResultMatchers.view().name("bookVisit"));
    }
}
