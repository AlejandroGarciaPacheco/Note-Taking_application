package CloudCompCW.SQL;

import java.sql.*;

public class SQL {

    static String path = "jdbc:mysql://cloudpad-db.cyeu5yz5ynyc.us-east-1.rds.amazonaws.com:3306/cloudComp";
    static String user = "admin";
    static String pass = "somemasterpassworD1-";
    static Connection conn;
    static
    {try {conn = DriverManager.getConnection(path, user, pass);}
    catch (SQLException e) {throw new RuntimeException(e);}}

    public static void write(String query)
    {
        try
        {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public static ResultSet read(String query)
    {
        try
        {
            Statement statement = conn.createStatement();
            return statement.executeQuery(query);
        }
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public static void main(String[] args) throws SQLException
    {
        System.out.println(conn.isValid(10));
        SQL.read("select * from Note");
    }
}