package pl.zbadajsie.przychodnia.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ExtendedModelMap;
import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.service.UserService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private SecurityContextFacade securityContextFacade;

    @Mock
    private UserService userServise;

    @Test
    void homePage() {
        String result = homeController.homePage();

        Assertions.assertEquals(result, "index");
    }

    @Test
    void loginPage() {
        ExtendedModelMap model = new ExtendedModelMap();

        String result = homeController.loginPage("error", model);

        Assertions.assertEquals(result, "login");
        Assertions.assertEquals(model.getAttribute("wrongUsernameOrPassword"), "Błęde hasło lub login!");
    }

    @Test
    void homePageLoginDoctor() {
        User user = mock(User.class);
        when(securityContextFacade.getLoggedInUser()).thenReturn(user);
        when(userServise.getRole(null)).thenReturn("DOCTOR");

        String result = homeController.homePageLogin();

        Assertions.assertEquals(result, "redirect:doctor/homepage");
    }

    @Test
    void homePageLoginPatient() {
        User user = mock(User.class);
        when(securityContextFacade.getLoggedInUser()).thenReturn(user);
        when(userServise.getRole(null)).thenReturn("PATIENT");

        String result = homeController.homePageLogin();

        Assertions.assertEquals(result, "redirect:patient/homepage");
    }

    @Test
    void homePageLogin() {
        when(securityContextFacade.getLoggedInUser()).thenReturn(null);

        String result = homeController.homePageLogin();

        Assertions.assertEquals(result, "login");
    }

    @Test
    void homePagePatient() {
        String result = homeController.homePagePatient();

        Assertions.assertEquals(result, "homePageForPatient");
    }

    @Test
    void homePageDoctor() {
        String result = homeController.homePageDoctor();

        Assertions.assertEquals(result, "homePageForDoctor");
    }

}