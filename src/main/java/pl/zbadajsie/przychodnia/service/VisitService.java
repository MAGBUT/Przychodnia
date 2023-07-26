package pl.zbadajsie.przychodnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zbadajsie.przychodnia.configuration.security.SecurityContextFacade;
import pl.zbadajsie.przychodnia.dto.*;
import pl.zbadajsie.przychodnia.dto.map.*;
import pl.zbadajsie.przychodnia.model.*;
import pl.zbadajsie.przychodnia.repository.DoctorRepository;
import pl.zbadajsie.przychodnia.repository.VisitRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final VisitDtoMapper visitDtoMapper;
    private final SecurityContextFacade securityContextFacade;
    private final UserService userService;
    private final DoctorInfoDtoMapper doctorInfoDtoMapper;
    private final NoteDtoMapper noteDtoMapper;
    private final ReferralDtoMapper referralDtoMapper;
    private final PrescriptionDtoMapper prescriptionDtoMapper;

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

    @Transactional
    public Optional<VisitDto> getVisit(Long id) {
        Person person = userService.getPerson();
        Set<Visit> visit = person.getVisit();
        if(visit.isEmpty()){
            return Optional.empty();
        }
        return visit.stream()
                .map(visitDtoMapper::map)
                .filter(visitDto -> visitDto.getId() == id)
                .findFirst();
    }

    @Transactional
    public boolean checkExist(Long id) {
        int id1 = Math.toIntExact(id);
        Set<Visit> visit = userService.getPerson().getVisit();
        List<Visit> list = visit.stream()
                .filter(visit1 -> visit1.getId() == id1)
                .toList();
        return !list.isEmpty();
    }

    @Transactional
    public DoctorInfoDto getDoctor(Long id) {
        Optional<Visit> byId = visitRepository.findById(id);
        Visit visit = byId.get();
        Set<Person> person = visit.getPerson();
        Optional<Person> doctor = person.stream()
                .filter(person1 -> person1.getDoctor() != null)
                .findFirst();
        return doctorInfoDtoMapper.mapDoctorInfo(doctor.get().getDoctor());
    }

    @Transactional
    public boolean visitWas(Long id) {
        Optional<Visit> byId = visitRepository.findById(id);
        Visit visit = byId.get();
        Note note = visit.getNote();
        return note != null;
    }

    @Transactional
    public NoteDto getNote(Long id) {
        Optional<Visit> byId = visitRepository.findById(id);
        Visit visit = byId.get();
        Note note = visit.getNote();
        return noteDtoMapper.map(note);
    }
    @Transactional
    public Optional<List<ReferralDto>> getReferral(Long id) {
        Optional<Visit> byId = visitRepository.findById(id);
        Visit visit = byId.get();
        Set<Referral> referrals = visit.getReferrals();
        if(referrals.isEmpty()){
            return Optional.empty();
        }
        List<ReferralDto> list = referrals.stream()
                .map(referralDtoMapper::map)
                .toList();
        return Optional.of(list);
    }
    @Transactional
    public Optional<List<PrescriptionDto>> getPrescription(Long id) {
        Optional<Visit> byId = visitRepository.findById(id);
        Visit visit = byId.get();
        Set<Prescription> prescriptions = visit.getPrescriptions();
        if(prescriptions.isEmpty()){
            return Optional.empty();
        }
        List<PrescriptionDto> list = prescriptions.stream()
                .map(prescriptionDtoMapper::map)
                .toList();
        return Optional.of(list);
    }

}
