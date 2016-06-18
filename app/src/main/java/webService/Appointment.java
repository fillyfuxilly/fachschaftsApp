package webService;

import java.util.GregorianCalendar;

/**
 * POJO, dass als Termin beim WebService persistiert ist.
 * @author Matthias Heinen
 *
 */
public class Appointment {
    /**
     * Titel des Termins
     */
    private String title;
    /**
     * Ort des Termins
     */
    private String location;
    /**
     * Datum des Termins
     */
    private GregorianCalendar date;
    /**
     * Beschreibung des Termins
     */
    private String description;
    /**
     * Gruppennummer des Termins, 0 fuer alle Gruppen
     */
    private int groupNr;

    public Appointment() {
    }

    public Appointment(String title, String location, GregorianCalendar date, String description, int groupNr) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.description = description;
        this.groupNr = groupNr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titel) {
        this.title = titel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String ort) {
        this.location = ort;
    }

    public GregorianCalendar getDate() { return date; }

    public void setDate(GregorianCalendar date) { this.date = date;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String beschreibung) {
        this.description = beschreibung;
    }

    public int getGroupNr() {
        return groupNr;
    }

    public void setGroupNr(int groupNr) {
        this.groupNr = groupNr;
    }
}
