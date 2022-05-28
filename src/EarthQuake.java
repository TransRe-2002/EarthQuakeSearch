import java.util.Date;
public class EarthQuake
{
    private int ID;
    private Date OT;
    private double LATITUDE;
    private double LONGITUDE;
    private double DEPTH;
    private double MAGNITUDE;
    private String REGION;

    public void setID(int ID)
    {
        this.ID = ID;
    }
    public void setOT(Date OT)
    {
        this.OT = OT;
    }

    public void setLATITUDE(double LATITUDE)
    {
        this.LATITUDE = LATITUDE;
    }

    public void setLONGITUDE(double LONGITUDE)
    {
        this.LONGITUDE = LONGITUDE;
    }

    public void setDEPTH(double DEPTH)
    {
        this.DEPTH = DEPTH;
    }

    public void setMAGNITUDE(double MAGNITUDE)
    {
        this.MAGNITUDE = MAGNITUDE;
    }

    public void setREGION(String REGION)
    {
        this.REGION = REGION;
    }

    public int getID()
    {
        return ID;
    }

    public Date getOT()
    {
        return OT;
    }

    public double getLATITUDE()
    {
        return LATITUDE;
    }

    public double getLONGITUDE()
    {
        return LONGITUDE;
    }

    public double getDEPTH()
    {
        return DEPTH;
    }

    public double getMAGNITUDE()
    {
        return MAGNITUDE;
    }

    public String getREGION()
    {
        return REGION;
    }

    @Override
    public String toString()
    {
        String s = OT.toString() + " Coordinate: ";
        if (LATITUDE < 0)
            s += -LATITUDE + "S";
        else
            s += LATITUDE + "N";
        if (LONGITUDE < 0)
            s += ", " + (-LATITUDE) + "W";
        else
            s += ", " + LATITUDE + "E";
        s += "Depth: " + DEPTH + "Magnitude: " + MAGNITUDE + " Region: " + REGION;
        return s;
    }

    @Override
    public boolean equals(Object o)
    {
        EarthQuake eq = null;
        if (o instanceof EarthQuake)
            eq = (EarthQuake) o;
        else
            return false;
        return ID == eq.ID && OT.equals(eq.OT) && LATITUDE == eq.LATITUDE && LONGITUDE == eq.LONGITUDE &&
                DEPTH == eq.DEPTH && MAGNITUDE == eq.MAGNITUDE && REGION.equals(eq.REGION);
    }
}