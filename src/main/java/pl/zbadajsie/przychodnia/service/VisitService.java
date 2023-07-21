package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.dto.DoctorInfoDto;
import pl.zbadajsie.przychodnia.dto.VisitDto;
import pl.zbadajsie.przychodnia.dto.map.VisitDtoMapper;
import pl.zbadajsie.przychodnia.model.Doctor;
import pl.zbadajsie.przychodnia.model.Person;
import pl.zbadajsie.przychodnia.model.Visit;
import pl.zbadajsie.przychodnia.repository.DoctorRepository;
import pl.zbadajsie.przychodnia.repository.VisitRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final VisitDtoMapper visitDtoMapper;
    private final SecurityContextFacade securityContextFacade;
    private final UserService userService;

    @Transactional
    public boolean freeDateTme(VisitDto dto) {
        OffsetDateTime offsetDateTime = dto.toOffsetDateTime();
        Doctor doctor = getDoctorFromVisit(dto);
        Set<Visit> visit = doctor.getPerson().getVisit();
        long count = visit.stream()
                .map(Visit::getDateTime)
                .filter(offsetDateTime1 -> offsetDateTime1.equals(offsetDateTime))
                .count();

        return count == 0;
    }

    private Doctor getDoctorFromVisit(VisitDto dto) {
        return doctorRepository.findAllById(dto.getDoctor_id());
    }

    @Transactional
    public void bookVisit(VisitDto dto) {
        Person person = userService.getPerson();
        Doctor doctorFromVisit = getDoctorFromVisit(dto);
        Visit visit = visitDtoMapper.map(dto, person, doctorFromVisit);
        visitRepository.save(visit);
        Set<Visit> visit2 = doctorFromVisit.getPerson().getVisit();
        Set<Visit> visit1 = person.getVisit();
        visit1.add(visit);
        visit2.add(visit);
    }

    @Transactional
    public Optional<List<VisitDto>> getVisit() {
        Person person = userService.getPerson();
        Set<Visit> visit = person.getVisit();
        if(visit.isEmpty()){
            return Optional.empty();
        }
        List<VisitDto> collect = visit.stream()
                .map(visitDtoMapper::map)
                .toList();
        return Optional.of(collect);
    }
}
