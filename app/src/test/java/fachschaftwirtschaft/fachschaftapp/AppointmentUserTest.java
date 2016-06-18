package fachschaftwirtschaft.fachschaftapp;


import org.junit.Before;
import org.junit.Test;
import java.util.GregorianCalendar;
import webService.Appointment;
import static org.junit.Assert.assertEquals;

/** Tests Appointment POJO
 * @author Matthias Heinen
 */
public class AppointmentUserTest {

    Appointment appointment;
    GregorianCalendar gc = new GregorianCalendar();

    @Before
    public void setUp() {
        appointment = new Appointment("Jenkins", "Im Internetz", gc, "Eine schlecht konfigurierte Lösung, die Studierende nerven soll", 0);
    }

    @Test
    public void manipulateAppointmentTest() {

        assertEquals(appointment.getDescription(), "Eine schlecht konfigurierte Lösung, die Studierende nerven soll");
        appointment.setDescription("Mal im ernst, wer hat sich das ausgedacht?");
        assertEquals(appointment.getDescription(), "Mal im ernst, wer hat sich das ausgedacht?");

        assertEquals(appointment.getTitle(), "Jenkins");
        appointment.setTitle("Unnütz");
        assertEquals(appointment.getTitle(), "Unnütz");

        GregorianCalendar gc2 = new GregorianCalendar();
        assertEquals(appointment.getDate(), gc);
        appointment.setDate(gc2);
        assertEquals(appointment.getDate(), gc2);

        assertEquals(appointment.getLocation(), "Im Internetz");
        appointment.setLocation("Besser gar nicht");
        assertEquals(appointment.getLocation(), "Besser gar nicht");

        assertEquals(appointment.getGroupNr(), 0);
        appointment.setGroupNr(1);
        assertEquals(appointment.getGroupNr(), 1);

    }

    @Test
    public void createAppointmentTest() {

        Appointment appointment2 = new Appointment("Jenkins", "Im Internetz", gc, "Nicht genug Hauptspeicher... is klar", 0);
        assertEquals(appointment2.getGroupNr(), 0);
        assertEquals(appointment2.getTitle(), "Jenkins");
        assertEquals(appointment2.getLocation(), "Im Internetz");
        assertEquals(appointment2.getDescription(), "Nicht genug Hauptspeicher... is klar");

    }
}
