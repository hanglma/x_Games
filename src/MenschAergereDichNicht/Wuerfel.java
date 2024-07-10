package MenschAergereDichNicht;

import java.util.Random;

public class Wuerfel
{
    public Wuerfel()
    {
    }
        public static void wuerfeln()
        {
            int min = 1;
            int max = 5;

            Random random = new Random();

            int x = random.nextInt(max + min) + min;
            System.out.println(x);
        }
}
// import MenschAergereDichNicht.Wuerfel;
//    Wuerfel wuerfel = new Wuerfel();
//        Wuerfel.wuerfeln();
