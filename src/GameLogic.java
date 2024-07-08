public class GameLogic
{
    // e=empty,also leer; x=X;c=0
    private String[][] feld = {{"e","e","e"},{"e","e","e"},{"e","e","e"}};
    public void check(String a,String b,String c) {
        if ( a=="x" &&  b=="x" &&  c=="x")
        {
            System.out.println("X hat gewonnen");
        }
        else if ( a=="c" &&  b=="c" &&  c=="c")
        {
            System.out.println("0 hat gewonnen");
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
