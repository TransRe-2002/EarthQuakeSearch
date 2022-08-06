
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EarthQuakeDao extends DBDao
{
    public static void add(EarthQuake eq) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("INSERT INTO EARTHQUAKE(ID, OT, LATITUDE, LONGITUDE, DEPTH, MAGNITUDE, REGION) " +
                "VALUES (?,?,?,?,?,?,?)");
        //参数传递
        pS.setInt(1,eq.getID());
        pS.setObject(2,eq.getOT());
        pS.setDouble(3,eq.getLATITUDE());
        pS.setDouble(4,eq.getLONGITUDE());
        pS.setDouble(5,eq.getDEPTH());
        pS.setDouble(6,eq.getMAGNITUDE());
        pS.setString(7, eq.getREGION());
        //执行语句
        pS.execute();
    }

    public static void update(EarthQuake eq) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("UPDATE EARTHQUAKE SET OT = ?, LATITUDE = ?, LONGITUDE = ?, DEPTH = ?, MAGNITUDE = ?, REGION = ? WHERE ID = ?");
        //参数传递
        pS.setObject(1,eq.getOT());
        pS.setDouble(2,eq.getLATITUDE());
        pS.setDouble(3,eq.getLONGITUDE());
        pS.setDouble(4,eq.getDEPTH());
        pS.setDouble(5,eq.getMAGNITUDE());
        pS.setString(6,eq.getREGION());
        pS.setInt(7,eq.getID());
        pS.execute();
    }

    public static void del(int ID) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("DELETE FROM EARTHQUAKE WHERE ID = ?");
        pS.setInt(1, ID);
        pS.execute();
    }

    public static void del(EarthQuake eq) throws SQLException
    {
        PreparedStatement pS = getConnection().prepareStatement("DELETE FROM EARTHQUAKE WHERE ID = ?");
        pS.setInt(1,eq.getID());
        pS.execute();
    }

    public static ArrayList<EarthQuake> quary() throws SQLException
    {
        Statement s = getConnection().createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM EARTHQUAKE");
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

    public static EarthQuake get(int ID) throws SQLException
    {
        EarthQuake eq = null;
        var pS = getConnection().prepareStatement("SELECT * FROM EARTHQUAKE WHERE ID = ?");
        pS.setInt(1,ID);
        var rs = pS.executeQuery();
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

        }
        return eq;
    }

    public static void addByCSV(String path) throws IOException,SQLException
    {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = reader.readLine()) != null)
        {
            EarthQuake eq = new EarthQuake();
            String[] item = line.split(",");
            String[] Stime = item[1].split("\\D");
            int[] Dt = new int[Stime.length];
            for (int i = 0; i < Stime.length; i++)
                Dt[i] = Integer.parseInt(Stime[i]);
            eq.setID(Integer.parseInt(item[0]));
            eq.setOT(LocalDateTime.of(Dt[0], Dt[1] ,Dt[2], Dt[3], Dt[4], Dt[5]));
            eq.setLATITUDE(Double.parseDouble(item[2]));
            eq.setLONGITUDE(Double.parseDouble(item[3]));
            eq.setDEPTH(Double.parseDouble(item[4]));
            eq.setMAGNITUDE(Double.parseDouble(item[5]));
            eq.setREGION(item[6]);
            add(eq);
        }
    }
}