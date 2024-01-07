package CloudCompCW.NotePadManager;

import CloudCompCW.SQL.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Notepad {

    static int count = 1;
    private final int notepadID;
    private String title;
    private final ArrayList<Note> notes = new ArrayList<>();

    public int addNote(String noteType)
    {
        Note created = new Note(count, interpretNoteType(noteType));
        SQL.write(
                String.format("insert into Note values (%d, %d, %d, ' ', '%s')", count, notepadID, NotepadManager.getUser(), noteType)
        );
        this.notes.add(created);
        count++;
        return created.getNoteID();
    }

    private void addNote(int id, String body, NoteType noteType)
    {
        Note created = new Note(id, body, noteType);
        this.notes.add(created);
        count++;
    }

    public void deleteNote(int id)
    {
        Note note = getNoteById(id);
        notes.remove(note);
        SQL.write("delete from Note where UserID = " + NotepadManager.getUser() + " and NotepadID = " + notepadID + " and NoteID = " + id);
    }

    public Note getNoteById(int id)
    {
        for (Note note : notes) if (note.getNoteID() == id) return note;
        throw new Error("Note not found for ID " + id + " in notepad " + notepadID);
    }

    int getNotepadID() { return notepadID; }

    public String getTitle() {return title;}

    public void setTitle(String title)
    {
        this.title = title;
        SQL.write(
                String.format("update Notepad set Title = '%s' where NotepadID = %d and UserID = %d",
                        title, notepadID, NotepadManager.getUser())
        );
    }

    static NoteType interpretNoteType(String s)
    {
        return switch (s)
                {
                    case "Plain" -> NoteType.Plain;
                    case "BulletList" -> NoteType.BulletList;
                    case "NumberedList" -> NoteType.NumberedList;
                    default -> throw new Error("couldnt interpret note type");
                };
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(String.format("\nNotepad: notepadID=%d, count=%d, title=%s, size=%d", notepadID, count, title, notes.size()));
        for (Note n : notes) s.append(n.toString());
        return s.toString();
    }
    
    void initialiseNotes(ResultSet rs) throws SQLException
    {
        while (rs.next())
        {
            this.addNote(
                    rs.getInt("NoteID"),
                    rs.getString("NoteBody"),
                    interpretNoteType(rs.getString("NoteType"))
            );
        }
    }


    public Notepad(int notepadID, String title)
    {
        this.notepadID = notepadID;
        this.title = title;
    }
}
