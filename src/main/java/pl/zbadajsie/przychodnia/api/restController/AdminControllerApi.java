package pl.zbadajsie.przychodnia.api.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zbadajsie.przychodnia.api.service.AdminService;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminControllerApi {

    private final AdminService adminService;
    @DeleteMapping("/delete/{userName}")
    ResponseEntity<?> delete(@PathVariable String userName){
        Boolean isDelete = adminService.deleteUser(userName);
        if(isDelete){
            return ResponseEntity.ok().body("User "+ userName + " isDelete");
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}
