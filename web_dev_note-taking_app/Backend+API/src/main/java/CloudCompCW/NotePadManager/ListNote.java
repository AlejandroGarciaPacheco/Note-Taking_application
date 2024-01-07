package CloudCompCW.NotePadManager;

public class ListNote extends Note {

    ListNote(int id, NoteType noteType)
    {
        super(id, noteType);
    }

    ListNote(int id, String body, NoteType noteType)
    {
        super(id, body, noteType);
    }
}
