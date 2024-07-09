package MenschAergereDichNicht;

public class SpielfigurRot extends Spielfigur
{
    public SpielfigurRot(int nummer)
    {
        super("rot",nummer,0,0);
        this.nummer = nummer;

    }


    @Override
    public void ziehen(int x) {
        super.ziehen(x);
    }
}
