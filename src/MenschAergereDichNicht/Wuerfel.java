package MenschAergereDichNicht;

import tile.TileManager;

import java.util.Random;
import javax.swing.Timer;

public class Wuerfel {
    public static int Wuerfelzahl;

    private final TileManager tileM;
    private final Timer animationTimer;  // Timer for animation


    public Wuerfel(TileManager tileM) {
        Wuerfelzahl = 0;
        this.tileM = tileM;
        animationTimer = new Timer(100, e -> animateRoll()); // 10ms interval
    }

    private void animateRoll() {
        tileM.mapTileNum[14][7] = (int) (Math.random() * 5 + 62); // Random face
        // (Optional) Add rotation or movement for a more dynamic animation
    }

    public int wuerfeln(int wuerfelgroese, int duration) {
        int min = 1;
        int max = wuerfelgroese - 1;

        Random random = new Random();

        animationTimer.setInitialDelay(0);
        animationTimer.setDelay(200); // 10ms interval for smoother animation
        animationTimer.setRepeats(true);
        animationTimer.restart();

        // Schedule a stop after the desired duration
        new Timer(duration, e -> animationTimer.stop()).start();

        Wuerfelzahl = random.nextInt(max + min) + min;
        return Wuerfelzahl;
    }
}
// import MenschAergereDichNicht.Wuerfel;
//    Wuerfel wuerfel = new Wuerfel();
//        Wuerfel.wuerfeln();
