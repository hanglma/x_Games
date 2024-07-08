package main;

import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 8;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 5;
    public final int getMaxScreenRow = 5;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * getMaxScreenRow;

    // FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);

    int playerX = 100;
    int playerY = 100;
    int pSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
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
            playerY -= pSpeed;
        } else if(keyH.downPressed){
            playerY += pSpeed;
        } else if(keyH.leftPressed){
            playerX -= pSpeed;
        } else if(keyH.rightPressed){
            playerX += pSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

//        g2.setColor(Color.white);
//        g2.fillRect(playerX, playerY,tileSize,tileSize);
//        g2.dispose();

    }
}
