package CloudCompCW.CloudPad;

import CloudCompCW.NotePadManager.Notepad;
import CloudCompCW.NotePadManager.NotepadManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/notepad")
public class NotepadAPI {

    @PostMapping(value = "/addNotepad")
    public static void addNotepad(String title)
    {
        System.out.println("Notepad added :" + title);
        NotepadManager.addNotepad(title);
    }

    @DeleteMapping(value = "/deleteNotepad")
    public void deleteNotepad(Integer id)
    {
        System.out.println("Deleting notepad :" + getNotepadByID(id));
        NotepadManager.deleteNotepad(id);
    }

    @GetMapping(value = "/getNotepads")
    public static HashMap<String, Integer> getNotepads()
    {
        System.out.println("Getting notepads");
        return NotepadManager.getNotepadsNamesAndIDs();
    }

    @GetMapping(value = "/notepadByID")
    public static Notepad getNotepadByID(Integer id)
    {
        System.out.println("Getting notepad by id: " + id);
        return NotepadManager.getNotepadByID(id);
    }

    @GetMapping("")
    public static String baseResponse()
    {
        System.out.println("notepad base");
        return "<h1>Notepad API endpoint</h1>";
    }
}
