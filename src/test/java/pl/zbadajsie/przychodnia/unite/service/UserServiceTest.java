package pl.zbadajsie.przychodnia.unite.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Role;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.UserRepository;
import pl.zbadajsie.przychodnia.service.UserService;

import static org.mockito.Mockito.*;
import java.util.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityContextFacade securityContextFacade;

    @Test
    void getRole(){
        Role role = Role.builder().role("DOCTOR").build();
        User user = User.builder().roles(Set.of(role)).build();

        when(userRepository.findByUserName(any())).thenReturn(user);

        String result = userService.getRole(any());

        Assertions.assertEquals(result, role.getRole());
    }

}
