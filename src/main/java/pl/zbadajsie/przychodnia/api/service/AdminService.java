package pl.zbadajsie.przychodnia.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.model.User;
import pl.zbadajsie.przychodnia.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    @Transactional
    public Boolean deleteUser(String userName) {
        Optional<User> userByUserName = userRepository.findUserByUserName(userName);
        if(userByUserName.isEmpty()){
            return false;
        }
        userRepository.deleteById((long) userByUserName.get().getId());
        return true;
    }
}
