import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
public class Main
{
    public static void main(String[] args) throws IOException, SQLException
    {
        var Leq = SearchDao.byDate(2008,5,12);
        for (EarthQuake eq : Leq)
            System.out.println(eq);
    }
}