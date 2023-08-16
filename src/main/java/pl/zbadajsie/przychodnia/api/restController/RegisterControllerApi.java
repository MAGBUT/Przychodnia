package pl.zbadajsie.przychodnia.api.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.zbadajsie.przychodnia.dto.PatientRegistrationDto;
import pl.zbadajsie.przychodnia.service.DoctorRegisterService;
import pl.zbadajsie.przychodnia.service.PatientRegisterService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegisterControllerApi {
    private final PatientRegisterService patientRegisterService;
    private final DoctorRegisterService doctorRegisterService;

    @PostMapping("/registerForPatient")
    ResponseEntity<?> registerForPatient(@Valid @RequestBody PatientRegistrationDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return "Field '" + fieldError.getField() + "': " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        boolean emailIsTake = patientRegisterService.checkEmail(dto.getEmail());
        boolean userNameIsTake = patientRegisterService.checkUserName(dto.getUserName());
        if (userNameIsTake || emailIsTake) {
            List<String> error = new ArrayList<>();
            if (userNameIsTake)
                error.add("Użytkownik o takiej nazwie już istnieje");
            if (emailIsTake)
                error.add("Użytkownik o takim emailu już istnieje");
            return ResponseEntity.badRequest().body(error);
        }
        patientRegisterService.register(dto);
        return ResponseEntity.ok().body(dto);
    }

}
