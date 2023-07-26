package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.InfoFromVisitDto;
import pl.zbadajsie.przychodnia.dto.NoteDto;
import pl.zbadajsie.przychodnia.model.Visit;

@Service
public class InfoFromVisitDtoMapper {
    public InfoFromVisitDto mapOnlyNote(NoteDto noteDto){
        return InfoFromVisitDto.builder()
                .noteDto(noteDto)
                .build();
    }
}
