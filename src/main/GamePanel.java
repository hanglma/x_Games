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


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Game loop
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            // only activates every 60 times per second
            if (delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    int currentPlayer = 1;
    MouseEvent lastMouseEvent = null;
    MouseEvent currentMouseEvent;
    int gameFinish = 0;

    public void update() {

        if(gameFinish == 0) {

            currentMouseEvent = mouseH.mouseClicked;

            if (currentMouseEvent != null && currentMouseEvent != lastMouseEvent) {

                MouseEvent mouseE = mouseH.mouseClicked;
                Point tilePoint = cordsToTile(mouseE.getX(), mouseE.getY());
                int curTileNum = tileM.mapTileNum[tilePoint.x][tilePoint.y];

                // System.out.println("Tile at " + tilePoint.x + ", " + tilePoint.y + " wurde geklickt!");

                if (tilePoint.x != 0 && tilePoint.x != 5 && tilePoint.y != 0 && tilePoint.y != 5) {
                    if (gameL.board[tilePoint.x - 1][tilePoint.y - 1] == 0) {
                        tileM.mapTileNum[tilePoint.x][tilePoint.y] = curTileNum + currentPlayer;
                        gameL.board[tilePoint.x - 1][tilePoint.y - 1] = currentPlayer;
                        gameL.stepUpCounter();
                        // System.out.println("Tile at " + tilePoint.x + "|" + tilePoint.y + " wurde geklickt!");
                    }
                }
                lastMouseEvent = currentMouseEvent;

                if (gameL.hasWinner()) {
                    System.out.println(currentPlayer + " won the game ;)");
                    gameFinish = currentPlayer;
                } else if (gameL.getCounter() == 9) {
                    gameFinish = 3;
                    System.out.println("Unentschieden :)");
                }

                if (currentPlayer == 1) {
                    currentPlayer++;
                } else {
                    currentPlayer--;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        switch (gameFinish) {
            case 0:
                tileM.draw(g2);
                // System.out.println("running ...");
                break;
            case 1:
                setBackground(Color.RED);
                // System.out.println("red");
                break;
            case 2:
                setBackground(Color.blue);
                // System.out.println("blue");
                break;
            case 3:
                setBackground(Color.GRAY);
                // System.out.println("----");
                break;
        }
    }

    public Point cordsToTile(int x, int y) {
        int tileX = Math.floorDiv(x, tileSize);
        int tileY = Math.floorDiv(y, tileSize);
        return new Point(tileX, tileY);
    }
}
