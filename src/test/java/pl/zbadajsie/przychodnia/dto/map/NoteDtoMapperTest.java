package pl.zbadajsie.przychodnia.dto.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zbadajsie.przychodnia.dto.NoteDto;
import pl.zbadajsie.przychodnia.model.Note;

@ExtendWith(MockitoExtension.class)
class NoteDtoMapperTest {

    @InjectMocks
    private NoteDtoMapper noteDtoMapper;
     @Mock
     private Note note;

     @Mock
     private NoteDto noteDto;

    @Test
    void mapNote() {
        Mockito.when(note.getId()).thenReturn(5);
        Mockito.when(note.getNoteTitle()).thenReturn("Tytuł");
        Mockito.when(note.getNoteDescription()).thenReturn("Opis");

        NoteDto dto = noteDtoMapper.map(note);

        Assertions.assertEquals(dto.getId(),note.getId());
        Assertions.assertEquals(dto.getTitle(),note.getNoteTitle());
        Assertions.assertEquals(dto.getDescription(),note.getNoteDescription());
    }

    @Test
    void mapDto() {
        Mockito.when(noteDto.getTitle()).thenReturn("Tytuł");
        Mockito.when(noteDto.getDescription()).thenReturn("Opis");

        Note dto = noteDtoMapper.map(noteDto);

        Assertions.assertEquals(dto.getNoteTitle(),noteDto.getTitle());
        Assertions.assertEquals(dto.getNoteDescription(),noteDto.getDescription());
    }
}