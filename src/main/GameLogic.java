package main;

public class GameLogic
{
    public GameLogic(){

    }

    private int counter=0;
    public int getCounter()
    {
        return counter;
    }
    public void setCounter(int i)
    {
        counter=i;
    }

    // 0 = empty,also leer; 1 = X; 2 = O

    public final int[][] feld = {{0,0,0},{0,0,0},{0,0,0}};

    public int check(int a,int b,int c)
    {
        if (a == b && a == c && a != 0) {
            return a;
        }
        else if (counter == 9) {
            return 3;
        }
        return 0;

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
