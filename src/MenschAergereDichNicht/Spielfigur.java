package MenschAergereDichNicht;

public class Spielfigur
{
    public String farbe;
    public int nummer;
    public int positionFeld;
    public int positionFarbe;
    public String bereich; //ob im Haus, auf dem Feld oder im Ziel
    public boolean schlagbar;

    public Spielfigur()
    {
        this.bereich = "imHaus";
        this.schlagbar = true;
    }


    public Spielfigur(String farbe, int nummer, int positionFeld, int positionFarbe)
    {
        this.bereich= "imHaus";
        this.farbe = farbe;
        this.nummer = nummer;
        this.positionFeld = positionFeld;
        this.positionFarbe = positionFarbe;
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
