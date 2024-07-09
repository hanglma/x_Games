package MenschAergereDichNicht;

public class SpielfigurBlau extends Spielfigur
{
    public SpielfigurBlau(int nummer)
    {
        super("blau", nummer,10,0);
        this.nummer = nummer;
    }

    @Override
    public void ziehen(int x)
    {
        super.ziehen(x);
    }

}
