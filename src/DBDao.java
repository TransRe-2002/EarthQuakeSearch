import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBDao
{
    private static String dbClassName = "org.mariadb.jdbc.Driver";
    private static String URL =  "jdbc:mariadb://localhost:3306/EARTHQUAKE";
    private static String name = "yuanx";
    private static String password = "2718281828";
    private static Connection conn = null;

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
