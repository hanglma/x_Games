package MenschAergereDichNicht;

public class Spielfigur
{
    public String farbe;
    public int nummer;
    public int positionFeld;
    public int positionQuadrant;
    public String bereich; //ob im Haus, auf dem Feld oder im Ziel

    public Spielfigur()
    {
        this.bereich = "imHaus";

    }
    public Spielfigur(String farbe, int nummer, int positionFeld, int positionQuadrant)
    {
        this.bereich= "imHaus";
        this.farbe = farbe;
        this.nummer = nummer;
        this.positionFeld = positionFeld;
        this.positionQuadrant = positionQuadrant;
    }
    public void ziehen( int x)
    {
        int NewPositionFeld;
        NewPositionFeld=positionFeld+x;
       if (NewPositionFeld<14)
       {
           positionFeld=NewPositionFeld;
       }
       else
       {
           positionQuadrant++;
           positionFeld=NewPositionFeld-13;

       }
    }
}
