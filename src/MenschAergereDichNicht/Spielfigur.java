package MenschAergereDichNicht;

public class Spielfigur
{
    public String farbe;
    public int nummer;
    public int positionFeld;
    public int positionFarbe;
    public boolean schlagbar;

    public Spielfigur()
    {
        this.schlagbar = true;
    }


    public Spielfigur(String farbe, int nummer, int positionFeld, int positionFarbe)
    {
        this.farbe = farbe;
        this.nummer = nummer;
        this.positionFeld = positionFeld;
        this.positionFarbe = positionFarbe;
        this.schlagbar = true;
    }


    public void ziehen( int x)
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
