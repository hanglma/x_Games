package MenschAergereDichNicht;

import java.util.Random;

public class Wuerfel
{
    public static int  Wuerfelzahl;
    public Wuerfel()
    {
        Wuerfelzahl = 0;

    }
        public static int wuerfeln()
        {
            int min = 1;
            int max = 5;

            Random random = new Random();

            int x = random.nextInt(max + min) + min;
            Wuerfelzahl= x;
            return Wuerfelzahl;
        }
}
// import MenschAergereDichNicht.Wuerfel;
//    Wuerfel wuerfel = new Wuerfel();
//        Wuerfel.wuerfeln();
