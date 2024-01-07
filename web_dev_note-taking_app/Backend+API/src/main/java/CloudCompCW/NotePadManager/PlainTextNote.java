package CloudCompCW.NotePadManager;

public class PlainTextNote extends Note {
    PlainTextNote(int id)
    {
        super(id, NoteType.Plain);
    }

    PlainTextNote(int id, String body)
    {
        super(id, body, NoteType.Plain);
    }
}
