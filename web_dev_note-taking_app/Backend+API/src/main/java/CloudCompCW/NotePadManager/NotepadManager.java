package CloudCompCW.NotePadManager;

import CloudCompCW.SQL.SQL;
import CloudCompCW.UserManager.User;
import CloudCompCW.UserManager.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class NotepadManager {

    private static final ArrayList<Notepad> notepads = new ArrayList<>();
    private static NotepadManager notepadManager;
    // todo users
    static private User user;
    private static int count = 1;

    public static NotepadManager getInstance()
    {
        if (notepadManager == null) try {notepadManager = new NotepadManager();} catch (SQLException e) {throw new RuntimeException(e);}
        return notepadManager;
    }

    private void addNotepad(int id, String title)
    {
        notepads.add(new Notepad(id, title));
        count++;
    }

    public static int addNotepad(String title)
    {
        Notepad created = new Notepad(count, title);
        notepads.add(created);
        SQL.write(String.format("insert into Notepad values (%d, %d, '%s')", count, user.getID(), title));
        count++;
        return created.getNotepadID();
    }

    public static void deleteNotepad(int id)
    {
        Notepad notepad = getNotepadByID(id);
        notepads.remove(notepad);
        SQL.write("delete from Notepad where NotepadID = " + id + " and UserID = " + user);
        SQL.write("delete from Note where NotepadID = " + id + " and UserID = " + user);
    }

    public static Notepad getNotepadByID(int id)
    {
        for (Notepad notepad : notepads) if (notepad.getNotepadID() == id) return notepad;
        throw new Error("Notepad not found for ID " + id);
    }

    static int getUser()
    {
        return user.getID();
    }

    public static HashMap<String, Integer> getNotepadsNamesAndIDs()
    {
        HashMap<String, Integer> hm = new HashMap<>();
        for (Notepad notepad : notepads) hm.put(notepad.getTitle(), notepad.getNotepadID());
        return hm;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Notepad Manager:\ncount=%d, user=%d, notepads=%d", count, user.getID(), notepads.size()));
        for (Notepad n : notepads) s.append(n.toString());
        return s.toString();
    }

    private NotepadManager() throws SQLException
    {
        user = UserManager.getCurrentUser();
        int userID = user.getID();
        ResultSet notepadRS = SQL.read("select * from Notepad where UserID = " + userID);
        while (notepadRS.next())
        {
            addNotepad(
                    notepadRS.getInt("NotepadID"),
                    notepadRS.getString("Title")
            );
        }

        for (Notepad notepad : notepads)
        {
            ResultSet noteRS = SQL.read(
                "select * from Note where " +
                        "NotepadID = " + notepad.getNotepadID() +
                        " and UserID = " + userID);
            notepad.initialiseNotes(noteRS);
        }
    }

    public static void reset()
    {
        notepads.clear();
        user = null;
        count = 1;
        notepadManager = null;
    }
}
