package webService;

import java.util.Collection;

/** POJO, dass als Nutzer beim WebService persistiert ist.
 * @author Matthias Heinen
 */
public class User {

    /**
     * Name des Nutzers.
     */
    private String username;
    /**
     * Gruppennummer des Nutzers.
     */
    private int groupNr;
    /**
     * Ist der Nutzer Administrator?.
     */
    private boolean isAdmin;

    public User() {
    }

    public User(String username, int groupNr) {
        this.username = username;
        this.groupNr = groupNr;

    }

    public String getUsername() {
        return username;
    }

    public int getGroupNr() {
        return groupNr;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
