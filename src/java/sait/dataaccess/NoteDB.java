package sait.dataaccess;

import sait.domainmodel.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NoteDB {

    public int insert(Note note) throws NotesDBException {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        Instant instant = note.getDateCreated().toInstant();
        ZoneId zoneid = ZoneId.of("America/Montreal");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneid);
        LocalDate localDate = zdt.toLocalDate();

        try {
            trans.begin();
            em.persist(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting note");
        } finally {
            em.close();
        }
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating note");
        } finally {
            em.close();
        }
    }

    public List<Note> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;
        } catch (Exception ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            em.close();
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param username The unique username.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Note getNote(int noteid) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Note note = em.find(Note.class, noteid);
            return note;
        } catch (Exception ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            em.close();
        }
    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting Note");
        } finally {
            em.close();
        }
    }
}
