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
    public void stepUpCounter() { counter += 1; }

    // 0 = empty,also leer; 1 = X; 2 = O

    public final int[][] board = {{0,0,0},{0,0,0},{0,0,0}};

//    public boolean check(int a,int b,int c)
//    {
//        if (a == b && a == c && a != 0) {
//            System.out.println(a + " gewinnt!");
//            return true;
//        }
//        System.out.println("Nichts passiert :)");
//        return false;
//
//    }
//    public int gewonnen() {
//        if (counter == 9) {
//            System.out.println("Unendschieden ;)");
//            return 3;
//        }
//        check(feld[0][0], feld[0][1], feld[0][2]);
//        check(feld[1][0], feld[1][1], feld[1][2]);
//        check(feld[2][0], feld[2][1], feld[2][2]);
//        check(feld[0][0], feld[1][0], feld[2][0]);
//        check(feld[0][1], feld[1][1], feld[2][1]);
//        check(feld[0][2], feld[1][2], feld[2][2]);
//        check(feld[0][0], feld[1][1], feld[2][2]);
//        check(feld[0][2], feld[1][1], feld[0][2]);
//        return 0;
//    }


    public boolean hasWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }
}
