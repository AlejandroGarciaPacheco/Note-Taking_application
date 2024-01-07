package CloudCompCW.NotePadManager;

public class BulletListNote extends Note {
    BulletListNote(int id)
    {
        super(id, NoteType.BulletList);
    }

    BulletListNote(int id, String body)
    {
        super(id, body, NoteType.BulletList);
    }
}
