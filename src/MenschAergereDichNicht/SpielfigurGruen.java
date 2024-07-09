package MenschAergereDichNicht;

public class SpielfigurGruen extends Spielfigur
{
    public SpielfigurGruen(int nummer)
    {
        super("gruen",nummer,30,0);
        this.nummer = nummer;

    }

    @Override
    public void ziehen(int x)
    {
        super.ziehen(x);
    }
}
