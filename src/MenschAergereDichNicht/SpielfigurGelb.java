package MenschAergereDichNicht;

public class SpielfigurGelb extends Spielfigur
{
    public SpielfigurGelb(int nummer)
    {
        super("gelb",nummer,20,0);
        this.nummer = nummer;

    }

    @Override
    public void ziehen(int x)
    {
        super.ziehen(x);
    }
}
