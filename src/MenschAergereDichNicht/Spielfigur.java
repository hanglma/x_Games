package MenschAergereDichNicht;

public class Spielfigur
{
    public String farbe;
    public int positionFeld;
    public int positionFarbe;
    public boolean schlagbar;
    public static int anzahlFiguren;



    public int getAnzahlFiguren()
    {
        return anzahlFiguren;
    }
    public void setAnzahlFiguren(int i)
    {
        anzahlFiguren=i;
    }


    public int getPositionFeld()
        {
            return positionFeld;
        }
    public void setPositionFeld(int i)
        {
            positionFeld=i;
        }


    public int getPositionFarbe()
        {
            return positionFarbe;
        }
    public void setPositionFarbe(int i)
        {
            positionFarbe=i;
        }



    public boolean getSchlagbar()
        {
            return schlagbar;
        }
    public void setSchlagbar(boolean i)
        {
            schlagbar=i;
        }


    public String getFarbe()
        {
            return farbe;
        }
    public void setFarbe(String i)
        {
            farbe=i;
        }











    public Spielfigur()
    {
        this.schlagbar = true;
    }


    public Spielfigur(String farbe, int positionFeld, int positionFarbe)
    {
        this.farbe = farbe;
        this.positionFeld = positionFeld;
        this.positionFarbe = positionFarbe;
        this.schlagbar = true;
        anzahlFiguren = 0;
    }


    public void ziehen(int x)
    {
        int NewPositionFarbe;
        int NewPositionFeld;
        NewPositionFeld = positionFeld + x;
        NewPositionFarbe=positionFarbe+x;
        if (NewPositionFarbe<40)
            {
                positionFarbe=NewPositionFarbe;
                if (NewPositionFeld<40)
                {
                    positionFeld=NewPositionFeld;
                }
                else
                {
                    positionFeld=NewPositionFeld-39; //evt. auch -40
                }
            }
        else
            {
               schlagbar = false;
            }



    }
}
