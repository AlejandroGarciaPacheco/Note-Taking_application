package CloudCompCW.NotePadManager;

public class NumberedListNote extends Note {

    NumberedListNote(int id)
    {
        super(id, NoteType.NumberedList);
    }

    NumberedListNote(int id, String body)
    {
        super(id, body, NoteType.NumberedList);
    }
}
