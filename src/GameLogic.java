import java.util.Objects;

public class GameLogic
{
    private int counter=0;
    public int getCounter()
    {
        return counter;
    }
    public void setCounter(int i)
    {
        counter=i;
    }

    // e=empty,also leer; x=X;c=0

    private String[][] feld = {{"e","e","e"},{"e","e","e"},{"e","e","e"}};
    public void check(String a,String b,String c)
    {
        if (Objects.equals(a,b) && Objects.equals(a,c))
        {
            System.out.println(a + " hat gewonnen");
        }
        else if (counter==9)
        {
            System.out.println("Unentschieden");
        }

    }
    public void gewonnen()
    {
        check(feld[0][0],feld[0][1],feld[0][2]);
        check(feld[1][0],feld[1][1],feld[1][2]);
        check(feld[2][0],feld[2][1],feld[2][2]);
        check(feld[0][0],feld[1][0],feld[2][0]);
        check(feld[0][1],feld[1][1],feld[2][1]);
        check(feld[0][2],feld[1][2],feld[2][2]);
        check(feld[0][0],feld[1][1],feld[2][2]);
        check(feld[0][2],feld[1][1],feld[0][2]);

    }

}
