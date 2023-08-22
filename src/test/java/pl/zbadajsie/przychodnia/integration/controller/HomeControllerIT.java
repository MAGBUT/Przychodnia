package pl.zbadajsie.przychodnia.integration.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.controller.HomeController;
import pl.zbadajsie.przychodnia.model.Role;
import pl.zbadajsie.przychodnia.service.UserService;

import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(HomeController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class HomeControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SecurityContextFacade securityContextFacade;

    @MockBean
    private UserService userServise;

    @Test
    @WithMockUser
    void homePage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }


    @Test
    @WithMockUser
    void homePageLogin() throws Exception {
        User user = mock(User.class);
        when(securityContextFacade.getLoggedInUser()).thenReturn(user);
        when(userServise.getRole(null)).thenReturn("PATIENT");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/homepage"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("patient/homepage"));
    }

    @Test
    @WithMockUser
    void homePagePatient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/homepage")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("homePageForPatient"));
    }

    @Test
    @WithMockUser
    void homePageDoctor() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/doctor/homepage")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("homePageForDoctor"));
    }
}
