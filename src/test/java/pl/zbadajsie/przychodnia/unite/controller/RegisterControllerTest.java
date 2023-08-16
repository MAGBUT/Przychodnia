package pl.zbadajsie.przychodnia.unite.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;
import pl.zbadajsie.przychodnia.controller.RegisterController;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.service.PatientRegisterService;
import pl.zbadajsie.przychodnia.service.DoctorRegisterService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    @InjectMocks
    private RegisterController registerController;

    @Mock
    private PatientRegisterService patientRegisterService;

    @Mock
    private DoctorRegisterService doctorRegisterService;

    @Test
    void register(){

        String result = registerController.register();


        Assertions.assertEquals(result,"register");
    }

    @Test
    void registerForPatient(){
        ExtendedModelMap model = new ExtendedModelMap();

        String result = registerController.registerForPatient(model);

        Assertions.assertEquals(result,"registerForPatient");
    }


    @Test
    void registerForDoctor(){
        ExtendedModelMap model = new ExtendedModelMap();
        when(doctorRegisterService.getAllSpecializations()).thenReturn(List.of("Dentysta"));

        String result = registerController.registerForDoctor(model);
        DoctorRegistrationDto doctor = (DoctorRegistrationDto)model.getAttribute("user");
        List<String> spec = doctor.getSpecialization();

        Assertions.assertEquals(result,"registerForDoctor");
        Assertions.assertEquals(spec,List.of("Dentysta"));
    }

    @Test
    void registerForPatientPostBinding(){
        ExtendedModelMap model = new ExtendedModelMap();
        BindingResult binding = mock(BindingResult.class);
        PatientRegistrationDto patient = new PatientRegistrationDto();

        when(binding.hasErrors()).thenReturn(true);

        String result = registerController.registerForPatient(patient,binding,model);

        Assertions.assertEquals(result,"registerForPatient");
    }

    @Test
    void registerForPatientPostWrongUserNameAndEmail(){
        ExtendedModelMap model = new ExtendedModelMap();
        BindingResult binding = mock(BindingResult.class);
        PatientRegistrationDto patient = PatientRegistrationDto.builder().userName("userName").email("email").build();

        when(binding.hasErrors()).thenReturn(false);
        when(patientRegisterService.checkEmail(patient.getEmail())).thenReturn(true);
        when(patientRegisterService.checkUserName(patient.getUserName())).thenReturn(true);


        String result = registerController.registerForPatient(patient,binding,model);

        Assertions.assertEquals(result,"registerForPatient");
        Assertions.assertEquals(model.getAttribute("wrongUsername"),"Użytkownik o takiej nazwie już isnieje");
        Assertions.assertEquals(model.getAttribute("wrongEmail"),"Użytkownik o takim emailu juz isnieje");
    }

    @Test
    void registerForPatientPost(){
        ExtendedModelMap model = new ExtendedModelMap();
        BindingResult binding = mock(BindingResult.class);
        PatientRegistrationDto patient = PatientRegistrationDto.builder().userName("userName").email("email").build();

        when(binding.hasErrors()).thenReturn(false);
        when(patientRegisterService.checkEmail(patient.getEmail())).thenReturn(false);
        when(patientRegisterService.checkUserName(patient.getUserName())).thenReturn(false);

        String result = registerController.registerForPatient(patient,binding,model);

        Assertions.assertEquals(result,"redirect:success");
    }

    @Test
    void success(){
        String result = registerController.success();

        Assertions.assertEquals(result,"registerSuccess");
    }
}
