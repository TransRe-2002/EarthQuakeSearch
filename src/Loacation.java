public class Loacation
{
    private String REGION;
    private String COUNTRY;
    private String FULLNAME;

    Loacation(String REGION, String COUNTRY, String FULLNAME)
    {
        this.REGION = REGION;
        this.COUNTRY = COUNTRY;
        this.FULLNAME = FULLNAME;
    }

    Loacation(String REGION, String COUNTRY)
    {
        this(REGION,COUNTRY,REGION + "_" + COUNTRY);
    }

    Loacation()
    {
        this(null,null,null);
    }
    public void setREGION(String REGION)
    {
        this.REGION = REGION;
    }

    public void setCOUNTRY(String COUNTRY)
    {
        this.COUNTRY = COUNTRY;
    }

    public void setFULLNAME(String FULLNAME)
    {
        this.FULLNAME = FULLNAME;
    }

    public String getREGION()
    {
        return REGION;
    }

    public String getCOUNTRY()
    {
        return COUNTRY;
    }

    public String getFULLNAME()
    {
        return FULLNAME;
    }

    public String toString()
    {
        return "Region: " + REGION + ", Country: " + COUNTRY + ", FULLNAME: " + FULLNAME + '\n';
    }

    public boolean equals(Object o)
    {
        Loacation l = null;
        if (o instanceof Loacation)
            l = (Loacation) o;
        else
            return false;
        return REGION.equals(l.REGION) && COUNTRY.equals(l.COUNTRY) && FULLNAME.equals(l.FULLNAME);
    }
}
