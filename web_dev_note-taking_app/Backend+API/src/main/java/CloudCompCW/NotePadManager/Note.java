package CloudCompCW.NotePadManager;

import CloudCompCW.SQL.SQL;

public class Note {

    private String content;
    private final NoteType noteType;
    private static int count = 0;
    private final int noteID;

    static Note createNote(NoteType noteType)
    {
        Note created;
        switch (noteType)
        {
            case Plain -> created = new  PlainTextNote(count);
            case BulletList -> created = new BulletListNote(count);
            case NumberedList -> created = new NumberedListNote(count);
            default -> throw new Error("Invalid Notetype for note creation");
        }
        count++;
        return created;
    }

    public int getNoteID() {return noteID;}

    public NoteType getNoteType() {return noteType;}

    @Override
    public String toString() {return String.format("NoteID=%d, type=%s, content=%s", noteID, noteType, content);}

    public String getContent() {return content;}

    public void setContent(String content)
    {
        this.content = content;
        SQL.write("update Note set NoteBody = '" + content + "' where NoteID = " + noteID);
    }

    Note(int id, NoteType noteType)
    {
        this.noteID = id;
        this.noteType = noteType;
    }

    Note(int id, String body, NoteType noteType)
    {
        this.noteID = id;
        this.content = body;
        this.noteType = noteType;
    }
}
