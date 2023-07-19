package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.model.Role;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.UserRepository;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public String getRole(String userName) {
        User user = userRepository.findByUserName(userName);
        String role = user.getRoles().stream()
                .limit(1)
                .map(Role::getRole)
                .collect(Collectors.joining());
        return role;
    }
}
