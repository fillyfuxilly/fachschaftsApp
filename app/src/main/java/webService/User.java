package webService;

import java.util.Collection;

/**
 * Created by Turnus on 09.06.2016.
 */
public class User {

    private static final long serialVersionUID = 1L;
    private int id;


    private String username;

    private int groupNr;
    private boolean isAdmin;


    private Collection<Appointment> appointments;

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

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
