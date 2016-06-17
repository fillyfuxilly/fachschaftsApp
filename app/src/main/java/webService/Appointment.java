package webService;

import java.util.GregorianCalendar;

/**
 * Ein Objekt, dass als Termin beim WebService persistiert ist.
 * @author Matthias Heinen
 *
 */
public class Appointment {

    private String title;
    private String location;
    private GregorianCalendar date;
    private String description;
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
