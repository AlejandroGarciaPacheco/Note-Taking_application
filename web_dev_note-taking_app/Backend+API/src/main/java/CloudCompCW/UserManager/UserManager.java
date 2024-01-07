package CloudCompCW.UserManager;

import CloudCompCW.SQL.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class UserManager {

    private static UserManager userManager;
    static private User currentUser;
    final static private ArrayList<User> users = new ArrayList<>();
    private static int count = 1;

    public static UserManager getInstance()
    {
        if (userManager == null) try {userManager = new UserManager();} catch (SQLException e) {throw new RuntimeException(e);}
        return userManager;
    }

    private void addUser(int id, String firstName, String lastName, Timestamp createdTimeStamp)
    {
        users.add(new User(id, firstName, lastName, createdTimeStamp));
        count++;
    }

    public static int addUser(String firstName, String lastName)
    {
        Timestamp createdTimeStamp = Timestamp.from(Instant.now());
        User created = new User(count, firstName, lastName, createdTimeStamp);
        users.add(created);
        SQL.write(String.format("insert into User values (%d, '%s', '%s', '%s', '%s')", count, firstName, lastName, createdTimeStamp, createdTimeStamp));
        count++;
        return created.getID();
    }

    public static void deleteUser(int userID)
    {
        User user = getUserById(userID);
        users.remove(user);
        SQL.write("delete from User where UserID = " + userID);
        SQL.write("delete from Notepad where UserID = " + userID);
        SQL.write("delete from Note where UserID = " + userID);
    }

    public static ArrayList<User> getUsers()
    {
        return users;
    }

    public static User getUserById(int id)
    {
        for (User user : users) if (user.getID() == id) return user;
        throw new Error("User not found for ID " + id);
    }

    public static boolean login(int userID)
    {
        try
        {
            currentUser = getUserById(userID);
        }
        catch (Error e)
        {
            return false;
        }
        SQL.write("update User set lastLogin = '" + Timestamp.from(Instant.now()) + "' where UserID = " + userID);
        return true;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) stringBuilder.append(user.toString());
        return stringBuilder.toString();
    }

    public UserManager() throws SQLException
    {
        ResultSet userRS = SQL.read("select * from User");
        while (userRS.next())
        {
            addUser(
                    userRS.getInt("UserID"),
                    userRS.getString("FirstName"),
                    userRS.getString("LastName"),
                    userRS.getTimestamp("CreatedTimeStamp")
            );
        }
    }

    public static void reset()
    {
        currentUser = null;
        users.clear();
        count = 1;
        userManager = null;
        getInstance();
    }
}
