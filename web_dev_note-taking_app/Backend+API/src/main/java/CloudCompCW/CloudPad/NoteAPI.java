package CloudCompCW.CloudPad;

import CloudCompCW.NotePadManager.Note;
import CloudCompCW.NotePadManager.NotepadManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteAPI {

    @PutMapping(value = "/addNote")
    public static int addNote(Integer notepadID, String type)
    {
        System.out.println("adding note of type :" + type + " to notepad of id: " + notepadID);
        return NotepadManager.getNotepadByID(notepadID).addNote(type);
    }

    @DeleteMapping(value = "/deleteNote")
    public static void deleteNote(Integer notepadID, Integer noteID)
    {
        System.out.println("deleteing note of id :" + noteID + " from notepad of id: " + notepadID);
        NotepadManager.getNotepadByID(notepadID).deleteNote(noteID);
    }

    @GetMapping(value = "/getNote")
    public static Note getNote(Integer notepadID, Integer noteID)
    {
        System.out.println("getting note of id: " + noteID + " of notepad of id: " + notepadID);
        return NotepadManager.getNotepadByID(notepadID).getNoteById(noteID);
    }

    @PostMapping(value = "/setTitle")
    public static void setTitle(Integer notepadID, String title)
    {
        System.out.println("Setting title to " + title + " of notepad of id: " + notepadID);
        NotepadManager.getNotepadByID(notepadID).setTitle(title);
    }

    @GetMapping("")
    public static String baseResponse()
    {
        System.out.println("base response note");
        return "<h1>Note API endpoint</h1>";
    }
}
