package fachschaftwirtschaft.fachschaftapp;

import org.junit.Before;
import org.junit.Test;
import webService.User;
import static org.junit.Assert.assertEquals;

/** Testet User POJO
 * @author Matthias Heinen
 */
public class UserUnitTest {

    User user2;

    /**
     * Erstellt vor den Tests einen User.
     */
    @Before
    public void setUp() {

        user2 = new User("Hans", 10);
    }

    /**
     * Testet ob ein User korrekt erstellt wird.
     */
    @Test
    public void createUserTest() {

        User user = new User("Jenkins", 2);
        assertEquals(user.getUsername(), "Jenkins");
        assertEquals(user.getGroupNr(), 2);
        assertEquals(user.isAdmin(), false);
    }

    /**
     * Testet, ob ein bestehender User korrekt manipuliert werden kann.
     */
    @Test
    public void manipulateUserTest() {

        assertEquals(user2.getUsername(), "Hans");
        user2.setUsername("Albers");
        assertEquals(user2.getUsername(), "Albers");

        assertEquals(user2.getGroupNr(), 10);
        user2.setGroupNr(1);
        assertEquals(user2.getGroupNr(), 1);

        assertEquals(user2.isAdmin(), false);
        user2.setIsAdmin(true);
        assertEquals(user2.isAdmin(), true);
    }
}
