package webService;


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

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setGroupNr(int groupNr) {
        this.groupNr = groupNr;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
