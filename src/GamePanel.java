import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int getMaxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * getMaxScreenRow;

    // FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    int px = 100;
    int py = 100;
    int pSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Game loop
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            // only activates every 60 times per second
            if(delta > 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        if(keyH.upPressed) {
            py -= pSpeed;
        } else if(keyH.downPressed){
            py += pSpeed;
        } else if(keyH.leftPressed){
            px -= pSpeed;
        } else if(keyH.rightPressed){
            px += pSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(px,py,tileSize,tileSize);
        g2.dispose();
    }
}
