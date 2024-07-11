package main;

import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 8;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 5;
    public final int getMaxScreenRow = 5;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * getMaxScreenRow;

    // FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    TileManager tileM = new TileManager(this);
    gameUI gameU = new gameUI(this);
    GameLogic gameL = new GameLogic();
    JButton playAgainButton;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);

        this.setLayout(new GridBagLayout()); // Set GridBagLayout for the panel


        // Create the Play Again button
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> restartGame());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL; // Center horizontally
        constraints.gridy = 1; // Place button in the second row (below game over text)

        // Add button with constraints
        this.add(playAgainButton, constraints);
        playAgainButton.setPreferredSize(new Dimension(2 * tileSize, tileSize/2));
        playAgainButton.setFont(new Font("Montserrat", Font.BOLD, tileSize/3));


        // Initially, the button is not visible
        playAgainButton.setVisible(false);
//        add(playAgainButton, BorderLayout.SOUTH);
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
    int gameState = 0;

    public void update() {

        if(gameState == 0) {

            currentMouseEvent = mouseH.mouseClicked;

            if (currentMouseEvent != null && currentMouseEvent != lastMouseEvent) {

                MouseEvent mouseE = mouseH.mouseClicked;
                Point tilePoint = cordsToTile(mouseE.getX(), mouseE.getY());
                int curTileNum = tileM.mapTileNum[tilePoint.x][tilePoint.y];

                // System.out.println("Tile at " + tilePoint.x + ", " + tilePoint.y + " wurde geklickt!");

                if (tilePoint.x != 0 && tilePoint.x != 4 && tilePoint.y != 0 && tilePoint.y != 4) {
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
                    gameState = currentPlayer;
                } else if (gameL.getCounter() == 9) {
                    gameState = 3;
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

        switch (gameState) {
            case 0:
                tileM.draw(g2);
                // System.out.println("running ...");
                break;
            case 1:
                setBackground(Color.RED);

                gameU.drawCenteredString(g2,"RED WINS ;)","Montserrat", 60);
                playAgainButton.setVisible(true);
                break;
            case 2:
                setBackground(Color.blue);
                // System.out.println("blue");
                gameU.drawCenteredString(g2,"BLUE WINS ;)","Montserrat", 60);
                playAgainButton.setVisible(true);
                break;
            case 3:
                setBackground(Color.GRAY);
                // System.out.println("----");
                gameU.drawCenteredString(g2,"Unentschieden :-)","Montserrat", 60);
                playAgainButton.setVisible(true);
                break;
        }
    }

    public Point cordsToTile(int x, int y) {
        int tileX = Math.floorDiv(x, tileSize);
        int tileY = Math.floorDiv(y, tileSize);
        return new Point(tileX, tileY);
    }

    // Method to restart the game
    private void restartGame() {
        gameState = 0;
        currentPlayer = 1;
        playAgainButton.setVisible(false);
        gameL.resetCounter();

        // Reset the mapTileNum array to its original state
        for (int i = 0; i < tileM.mapTileNum.length; i++) {
            System.arraycopy(tileM.originalMap[i], 0, tileM.mapTileNum[i], 0, tileM.originalMap[i].length);
        }

        // Reset the game state here
        for (int[] row : gameL.board) {
            Arrays.fill(row, 0);
        }

        // Debug
        for (int[] row : gameL.board) {
            System.out.println(Arrays.toString(row));
        }
        for(int[] row: tileM.mapTileNum){
            System.out.println(Arrays.toString(row));
        }

        repaint();
    }
}


