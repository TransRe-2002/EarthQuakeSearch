import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDao extends DBDao
{
    public static void add(Loacation l) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("INSERT INTO LOCATION(REGION, COUNTRY, FULLNAME) VALUES(?,?,?)");

        pS.setString(1,l.getREGION());
        pS.setString(2,l.getCOUNTRY());
        pS.setString(3,l.getFULLNAME());

        pS.execute();
    }

    public static void update(Loacation l) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("UPDATE LOCATION SET COUNTRY = ?,FULLNAME = ? WHERE REGION = ?");
        pS.setString(1,l.getCOUNTRY());
        pS.setString(2,l.getFULLNAME());
        pS.setString(3,l.getREGION());

        pS.execute();
    }

    public static void del(String REGION) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("DELETE FROM LOCATION WHERE REGION = ?");
        pS.setString(1,REGION);
        pS.execute();
    }

    public static void del(Loacation l) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("DELETE FROM LOCATION WHERE REGION = ?");
        pS.setString(1,l.getREGION());
        pS.execute();
    }

    public static List<Loacation> quary() throws SQLException
    {
        Statement s = getConnection().createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM LOCATION");
        var Ll = new ArrayList<Loacation>();
        Loacation l = null;
        while (rs.next())
        {
            l = new Loacation();
            l.setREGION(rs.getString("REGION"));
            l.setCOUNTRY(rs.getString("COUNTRY"));
            l.setFULLNAME(rs.getString("FULLNAME"));

            Ll.add(l);
        }
        return Ll;
    }

    public static Loacation get(String REGION) throws SQLException
    {
        Loacation l = null;
        PreparedStatement pS = getConnection().prepareStatement("SELECT * FROM LOCATION WHERE REGION = ?");
        pS.setString(1,REGION);
        var rs = pS.executeQuery();
        while (rs.next())
        {
            l = new Loacation();
            l.setREGION(rs.getString("REGION"));
            l.setCOUNTRY(rs.getString("COUNTRY"));
            l.setFULLNAME(rs.getString("FULLNAME"));
        }
        return l;
    }

    public static void addByCSV(String path) throws IOException,SQLException
    {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = reader.readLine()) != null)
        {
            Loacation l = new Loacation();
            String[] item = line.split(",");
            l.setREGION(item[0]);
            l.setCOUNTRY(item[1]);
            l.setFULLNAME(item[3]);
            add(l);
        }
    }
}
