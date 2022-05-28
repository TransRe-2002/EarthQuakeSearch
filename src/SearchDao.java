import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDao extends DBDao
{
    public static List<EarthQuake> byREGION(String REGION) throws SQLException
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
            eq.setOT(new java.util.Date(rs.getDate("OT").getTime()));
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }

    public static List<EarthQuake> byCountry(String COUNTRY) throws SQLException
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
            eq.setOT(new java.util.Date(rs.getDate("OT").getTime()));
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }

    public static List<EarthQuake> byDate(int year, int month, int day) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement
                (
                "SELECT * FROM EARTHQUAKE x WHERE (? = 0 OR YEAR(x.OT) = ?) " +
                        "AND (SELECT * FROM EARTHQUAKE y WHERE YEAR(x.OT) = YEAR(y.OT) " +
                        "AND (? = 0 OR MONTH(y.OT) = ?) " +
                        "AND (SELECT * FROM EARTHQUAKE z WHERE MONTH(z.OT) = MONTH(y.OT) " +
                        "AND (? = 0 OR DAY(z.OT) = ?)))"
                );
        pS.setInt(1,year);
        pS.setInt(2,year);
        pS.setInt(3,month);
        pS.setInt(4,month);
        pS.setInt(5,day);
        pS.setInt(6,day);
        ResultSet rs = pS.executeQuery();
        var Leq = new ArrayList<EarthQuake>();
        EarthQuake eq = null;
        while (rs.next())
        {
            eq = new EarthQuake();
            eq.setID(rs.getInt("ID"));
            eq.setOT(new java.util.Date(rs.getDate("OT").getTime()));
            eq.setLATITUDE(rs.getDouble("LATITUDE"));
            eq.setLONGITUDE(rs.getDouble("LONGITUDE"));
            eq.setDEPTH(rs.getDouble("DEPTH"));
            eq.setMAGNITUDE(rs.getDouble("MAGNITUDE"));
            eq.setREGION(rs.getString("REGION"));

            Leq.add(eq);
        }
        return Leq;
    }

    public static List<EarthQuake> byYearMonth(int year, int month) throws SQLException
    {
        return byDate(year,month,0);
    }

    public static List<EarthQuake> byYear(int year) throws SQLException
    {
        return byDate(year,0,0);
    }

    public static List<EarthQuake> byYearDay(int year, int day) throws SQLException
    {
        return byDate(year,0, day);
    }

    public static List<EarthQuake> byMonthDay(int month, int day) throws SQLException
    {
        return byDate(0, month, day);
    }

    public static List<EarthQuake> byMonth(int month) throws SQLException
    {
        return byDate(0, month,0);
    }

    public static List<EarthQuake> byDay(int day) throws SQLException
    {
        return byDate(0,0, day);
    }

    public static List<EarthQuake> byDate() throws SQLException
    {
        return byDate(0,0,0);
    }

    public static List<EarthQuake> byCoordinate(double LATITUDE, double LONGITUDE) throws SQLException
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
            eq.setOT(new java.util.Date(rs.getDate("OT").getTime()));
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
