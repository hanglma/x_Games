package MenschAergereDichNicht;

import java.util.HashMap;
import java.util.Map;

public class Spielfeld
{
    public Map<Integer, Integer> spielfeldAll;

    public boolean feststellen;

    public int Feldall, Farbnum;
    public int i, l, pos, farbp;
    public String farb;

    public Spielfeld(int Feldall, String farb)
    {
        this.farb = farb;
        this.Feldall = Feldall;

        if(farb == "rot")
        {
            Farbnum = 1;
        }
        if(farb == "blau")
        {
            Farbnum = 2;
        }
        if(farb == "gelb")
        {
            Farbnum = 3;
        }
        if(farb == "gruen")
        {
            Farbnum = 4;
        }else
        {
            Farbnum = 0;
        }
        spielfeldAll.put(Feldall, Farbnum);
    }

}
