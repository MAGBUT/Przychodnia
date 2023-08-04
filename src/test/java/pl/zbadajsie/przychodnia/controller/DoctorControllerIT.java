package pl.zbadajsie.przychodnia.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.ui.ExtendedModelMap;
//import pl.zbadajsie.przychodnia.dto.VisitDoctorDto;
//import pl.zbadajsie.przychodnia.service.DoctorService;
//import pl.zbadajsie.przychodnia.service.VisitService;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(controllers = DoctorController.class)
//@AllArgsConstructor(onConstructor = @__(@Autowired))
//public class DoctorControllerIT {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private DoctorController doctorController;
//
//    @MockBean
//    private DoctorService doctorService;
//
//    @MockBean
//    private VisitService visitService;
//
//    @ParameterizedTest
//    @MethodSource
//    void getVisitTest(){
//        Optional<List<VisitDoctorDto>> visit = Optional.of(List.of(new VisitDoctorDto(),new VisitDoctorDto()));
//        ExtendedModelMap model = new ExtendedModelMap();
//
//        when(doctorService.getVisit()).thenReturn(visit);
//
//        String result = doctorController.getVisit(model);
//
//        Assertions.assertEquals(result,"visitDoctor");
//        Assertions.assertEquals(visit.get(),model.getAttribute("visits"));
//    }
//}
