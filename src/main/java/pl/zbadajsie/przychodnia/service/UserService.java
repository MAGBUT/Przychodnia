package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Role;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.UserRepository;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityContextFacade securityContextFacade;

    public String getRole(String userName) {
        User user = userRepository.findByUserName(userName);
        String role = user.getRoles().stream()
                .limit(1)
                .map(Role::getRole)
                .collect(Collectors.joining());
        return role;
    }



    @Transactional
    public Person getPerson(){
        org.springframework.security.core.userdetails.User user = securityContextFacade.getLoggedInUser();
        User user1 = userRepository.findByUserName(user.getUsername());
        return user1.getPerson();
    }
}
