package pl.zbadajsie.przychodnia.integration.restController;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.zbadajsie.przychodnia.api.restController.DoctorControllerApi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DoctorControllerApi.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorControllerIT {

    private MockMvc mockMvc;

    @Test
    void getVisit() throws Exception {

        String endpion = "/api/get";
        mockMvc.perform(get(endpion))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
