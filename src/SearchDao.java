import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDao extends DBDao
{
    public static ArrayList<EarthQuake> byRegion(String REGION) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("SELECT * FROM EARTHQUAKE WHERE REGION = ?");
        pS.setString(1,REGION);
        ResultSet rs = pS.executeQuery();
        var Leq = new ArrayList<EarthQuake>();
        EarthQuake eq = null;
        while (rs.next())
        {
            eq = new EarthQuake();
            eq.setID(rs.getInt("ID"));
            eq.setOT(rs.getTimestamp("OT").toLocalDateTime());
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }

    public static ArrayList<EarthQuake> byCountry(String COUNTRY) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("SELECT * FROM EARTHQUAKE,LOCATION WHERE EARTHQUAKE.REGION = LOCATION.REGION AND LOCATION.COUNTRY = ?");
        pS.setString(1,COUNTRY);
        ResultSet rs = pS.executeQuery();
        var Leq = new ArrayList<EarthQuake>();
        EarthQuake eq = null;
        while (rs.next())
        {
            eq = new EarthQuake();
            eq.setID(rs.getInt("ID"));
            eq.setOT(rs.getTimestamp("OT").toLocalDateTime());
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }

    public static ArrayList<EarthQuake> byDate(int year, int month, int day) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement
                (
                "SELECT * FROM EARTHQUAKE WHERE YEAR(OT) = ? AND MONTH(OT) = ? AND DAY(OT) = ?"
                );
        pS.setInt(1,year);
        pS.setInt(2,month);
        pS.setInt(3,day);

        ResultSet rs = pS.executeQuery();
        var Leq = new ArrayList<EarthQuake>();
        EarthQuake eq = null;
        while (rs.next())
        {
            eq = new EarthQuake();
            eq.setID(rs.getInt("ID"));
            eq.setOT(rs.getTimestamp("OT").toLocalDateTime());
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }

    public static ArrayList<EarthQuake> byCoordinate(double LATITUDE, double LONGITUDE) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("SELECT * FROM EARTHQUAKE WHERE LATITUDE = ROUND(?,6) AND LONGITUDE = ROUND(?,7)");
        pS.setDouble(1,LATITUDE);
        pS.setDouble(2,LONGITUDE);
        ResultSet rs = pS.executeQuery();
        var Leq = new ArrayList<EarthQuake>();
        EarthQuake eq = null;
        while (rs.next())
        {
            eq = new EarthQuake();
            eq.setID(rs.getInt("ID"));
            eq.setOT(rs.getTimestamp("OT").toLocalDateTime());
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }
}
