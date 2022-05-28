import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBDao
{
    private static String dbClassName = "org.mariadb.jdbc.Driver";
    private static String URL =  "jdbc:mariadb://localhost:3306/Earthquake";
    private static String name = "yuanx";
    private static String password = "2718281828";
    private static Connection conn = null;
    static
    {
        try
        {
            Class.forName(dbClassName);
            conn = DriverManager.getConnection(URL,name,password);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
}
