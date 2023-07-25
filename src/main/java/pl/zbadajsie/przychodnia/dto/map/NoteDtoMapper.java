package pl.zbadajsie.przychodnia.dto.map;

import org.springframework.stereotype.Service;
import pl.zbadajsie.przychodnia.dto.NoteDto;
import pl.zbadajsie.przychodnia.model.Note;
@Service
public class NoteDtoMapper {
    public NoteDto map(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getNoteTitle())
                .description(note.getNoteDescription())
                .build();
    }
}
