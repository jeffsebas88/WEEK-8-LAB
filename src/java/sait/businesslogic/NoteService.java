package sait.businesslogic;

import sait.dataaccess.NoteDB;
import sait.domainmodel.Note;
import java.util.Date;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteid) throws Exception {
        return noteDB.getNote(noteid);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteid, String contents) throws Exception {
        Note note = noteDB.getNote(noteid);
        note.setContent(contents);
        return noteDB.update(note);
    }

    public int delete(int noteid) throws Exception {
        Note deletedNote = noteDB.getNote(noteid);
        return noteDB.delete(deletedNote);
    }

    public int insert(String contents) throws Exception {
        Date date = new Date();
        Note note = new Note(date,contents);
        return noteDB.insert(note);
    }
}
