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
import pl.zbadajsie.przychodnia.api.dto.QuestionDto;
import pl.zbadajsie.przychodnia.controller.RegisterController;
import pl.zbadajsie.przychodnia.dto.DoctorRegistrationDto;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.service.DoctorRegisterService;
import pl.zbadajsie.przychodnia.service.PatientRegisterService;
import pl.zbadajsie.przychodnia.testData.DoctorDtoTestData;
import pl.zbadajsie.przychodnia.testData.PatientDtoTestData;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(RegisterController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RegisterControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PatientRegisterService patientRegisterService;

    @MockBean
    private DoctorRegisterService doctorRegisterService;

    @Test
    @WithMockUser
    void register() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));

    }

    @Test
    @WithMockUser
    void registerForPatient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/registerForPatient"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("registerForPatient"));

    }

    @Test
    @WithMockUser
    void registerForDoctor() throws Exception {

        when(doctorRegisterService.getAllSpecializations()).thenReturn(List.of("Specialization"));

        mockMvc.perform(MockMvcRequestBuilders.get("/registerForDoctor"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("registerForDoctor"));

    }

    @Test
    @WithMockUser
    void registerForPatientPostIsTake() throws Exception {
        PatientRegistrationDto dto = PatientDtoTestData.somePatientDto1();
        when(patientRegisterService.checkEmail(dto.getEmail())).thenReturn(true);
        when(patientRegisterService.checkUserName(dto.getUserName())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registerForPatient")
                        .flashAttr("user",dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("wrongUsername"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("wrongEmail"))
                .andExpect(MockMvcResultMatchers.view().name("registerForPatient"));

    }

    @Test
    @WithMockUser
    void registerForPatientPostBinding() throws Exception {
        PatientRegistrationDto dto = PatientRegistrationDto.builder().name("Name").build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registerForPatient")
                        .flashAttr("user",dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registerForPatient"));

    }

    @Test
    @WithMockUser
    void registerForPatientPost() throws Exception {
        PatientRegistrationDto dto = PatientDtoTestData.somePatientDto1();
        when(patientRegisterService.checkEmail(dto.getEmail())).thenReturn(false);
        when(patientRegisterService.checkUserName(dto.getUserName())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registerForPatient")
                        .flashAttr("user",dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("success"));

    }

    @Test
    @WithMockUser
    void registerForDoctorPost() throws Exception {
        DoctorRegistrationDto dto = DoctorDtoTestData.someDoctorDto1();
        when(doctorRegisterService.checkEmail(dto.getEmail())).thenReturn(false);
        when(doctorRegisterService.checkUserName(dto.getUserName())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registerForDoctor")
                        .flashAttr("user",dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("success"));

    }

    @Test
    @WithMockUser
    void registerForDoctorPostBinding() throws Exception {
        DoctorRegistrationDto dto = DoctorRegistrationDto.builder().name("Name").build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registerForDoctor")
                        .flashAttr("user",dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registerForDoctor"));

    }

    @Test
    @WithMockUser
    void registerForDoctorPostIsTake() throws Exception {
        DoctorRegistrationDto dto = DoctorDtoTestData.someDoctorDto1();
        when(doctorRegisterService.checkEmail(dto.getEmail())).thenReturn(true);
        when(doctorRegisterService.checkUserName(dto.getUserName())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registerForDoctor")
                        .flashAttr("user",dto)
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("wrongUsername"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("wrongEmail"))
                .andExpect(MockMvcResultMatchers.view().name("registerForDoctor"));

    }

    @Test
    @WithMockUser
    void success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/success")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registerSuccess"));

    }
}
