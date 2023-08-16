package pl.zbadajsie.przychodnia.api.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zbadajsie.przychodnia.dto.PatientInfoDto;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorControllerApi {

    @GetMapping("/get")
    public ResponseEntity<?> getAllPatient() {
        PatientInfoDto patientInfoDto = PatientInfoDto.builder()
                .userName("userName")
                .firstName("firstName")
                .lastName("lastName")
                .email("email@email.pl")
                .build();
        return ResponseEntity.ok().body(patientInfoDto);
    }

}
