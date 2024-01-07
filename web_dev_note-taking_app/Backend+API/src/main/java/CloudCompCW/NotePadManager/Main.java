package CloudCompCW.NotePadManager;

import CloudCompCW.UserManager.UserManager;

public class Main {
    public static void main(String[] args)
    {
        UserManager userManager = UserManager.getInstance();
        UserManager.addUser("test", "test");
        UserManager.login(1);
        NotepadManager notepadManager = NotepadManager.getInstance();
        int test_np = NotepadManager.addNotepad("Test notepad");
        int test_note = NotepadManager.getNotepadByID(test_np).addNote("Plain");
        NotepadManager.getNotepadByID(test_np).getNoteById(test_note).setContent("Testing Testing 123");
        System.out.println(notepadManager);
    }
}