package CloudCompCW.UserManager;

import java.sql.Timestamp;

public class User {

    private final int ID;
    private final String firstName, lastname;
    private final Timestamp createdTimeStamp;

    public int getID()
    {
        return ID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastname()
    {
        return lastname;
    }

    @Override
    public String toString()
    {
        return "\nUser " + ID + " first " + firstName + " last " + lastname;
    }

    public User(int ID, String firstName, String lastname, Timestamp createdTimeStamp)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.createdTimeStamp = createdTimeStamp;
    }
}
