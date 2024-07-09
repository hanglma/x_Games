package main;

import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;

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
    MouseHandler mouseH = new MouseHandler();
    TileManager tileM = new TileManager(this);
    GameLogic gameL = new GameLogic();


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
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

    int currentPlayer = 1;
    MouseEvent lastMouseEvent = null;
    MouseEvent currentMouseEvent;

    public void update(){

        currentMouseEvent = mouseH.mouseClicked;

        if(currentMouseEvent != null && currentMouseEvent != lastMouseEvent){

            MouseEvent mouseE = mouseH.mouseClicked;
            Point tilePoint = cordsToTile(mouseE.getX(),mouseE.getY());
            if(gameL.feld[tilePoint.x][tilePoint.y] != 0){
                tileM.mapTileNum[tilePoint.x][tilePoint.y] = currentPlayer + 0;
                gameL.feld[tilePoint.x][tilePoint.y] = currentPlayer;

                if(currentPlayer == 1){
                    currentPlayer++;
                } else{
                    currentPlayer--;
                }
            }

            lastMouseEvent = currentMouseEvent;
        }
        gameL.gewonnen();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

//        g2.setColor(Color.white);
//        g2.fillRect(playerX, playerY,tileSize,tileSize);
//        g2.dispose();
    }

    public Point cordsToTile(int x, int y){
        int tileX = Math.floorDiv(x, tileSize);
        int tileY = Math.floorDiv(y, tileSize);
        return new Point(tileX,tileY);
    }
}
