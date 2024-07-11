package main;

import MenschAergereDichNicht.Wuerfel;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    int scale = 8;

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 5;
    public int getMaxScreenRow = 5;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * getMaxScreenRow;

    public int currentGame = 0;

    private final JFrame parentFrame;

    // FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    TileManager tileM = new TileManager(this);
    gameUI gameU = new gameUI(this);
    GameLogic gameL = new GameLogic();


    JButton playAgainButton;
    JButton backButton;
    JButton tictactoeButton;
    JButton menschButton;
    JButton wuerfelButton;

    Wuerfel wuerfel = new Wuerfel(tileM);

    private final int buttonOffsetX;

    public GamePanel(JFrame parentFrame) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        this.setLayout(null);

        this.parentFrame = parentFrame;


        // Create the Play Again button
        playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.addActionListener(e -> {
            restartGames();
            tileM.switchMap(1);
        });

        // Create the TicTacToe button
        tictactoeButton = new JButton("TIC TAC TOE");
        tictactoeButton.addActionListener(e -> {
            restartGames();
            currentGame = 1;
            tileM.switchMap(1);

            tictactoeButton.setVisible(false);
            menschButton.setVisible(false);
        });

        // Create the Menschäregere dich nicht button
        menschButton = new JButton("MENSCH ÄRGERE");
        menschButton.addActionListener(e ->{
            restartGames();
            currentGame = 2;
            tileM.switchMap(2);

            tictactoeButton.setVisible(false);
            menschButton.setVisible(false);

            backButton.setBounds(tileSize*14,tileSize,2*tileSize,tileSize/2);
            backButton.setVisible(true);

            wuerfelButton.setBounds(tileSize * 27 / 2 , tileSize*17/2,tileSize*5/2,tileSize/2);
            wuerfelButton.setVisible(true);
        });

        // Create back button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            restartGames();
            currentGame = 0;
            tileM.switchMap(0);

            tictactoeButton.setVisible(true);
            menschButton.setVisible(true);
            playAgainButton.setVisible(false);
            backButton.setVisible(false);
        });

        // Create Würfel button
        wuerfelButton = new JButton("Würfeln");
        wuerfelButton.addActionListener(e -> {
            wuerfel.wuerfeln(6, 2000);
        });


        // calculate button offsets
        buttonOffsetX = (screenWidth - 2*tileSize)/2;

        // Add buttons + set positions
        this.add(playAgainButton);
        playAgainButton.setBounds(buttonOffsetX, screenHeight/2,2*tileSize, tileSize/2);
        playAgainButton.setFont(new Font("Montserrat", Font.BOLD, tileSize/6));

        this.add(tictactoeButton);
        tictactoeButton.setBounds(buttonOffsetX, screenHeight/2 - tileSize/3,2*tileSize, tileSize/2);
        tictactoeButton.setFont(new Font("Montserrat", Font.BOLD, tileSize/6));

        this.add(menschButton);
        menschButton.setBounds(buttonOffsetX, screenHeight/2 + tileSize/3,2*tileSize, tileSize/2);
        menschButton.setFont(new Font("Montserrat", Font.BOLD, tileSize/6));

        this.add(backButton);
        backButton.setBounds(buttonOffsetX, screenHeight/2 + tileSize/2,2*tileSize, tileSize/2);
        backButton.setFont(new Font("Montserrat", Font.BOLD, tileSize/6));

        this.add(wuerfelButton);
        wuerfelButton.setFont(new Font("Montserrat", Font.BOLD, tileSize/6));


        // Initially, the button is not visible
        playAgainButton.setVisible(false);
        backButton.setVisible(false);
        wuerfelButton.setVisible(false);

        // Initially, choose buttons are visible
        tictactoeButton.setVisible(true);
        menschButton.setVisible(true);
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
    int tictactoeState = 0;
    int lastGame = 0;

    public void update() {

        switch (currentGame){
            case 0:
                break;

            case 1:

                if (tictactoeState == 0) {

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

                                if (gameL.hasWinner()) {
                                    System.out.println(currentPlayer + " won the game ;)");
                                    tictactoeState = currentPlayer;
                                } else if (gameL.getCounter() == 9) {
                                    tictactoeState = 3;
                                    System.out.println("Unentschieden :)");
                                }

                                if (currentPlayer == 1) {
                                    currentPlayer++;
                                } else {
                                    currentPlayer--;
                                }
                            }
                        }
                        lastMouseEvent = currentMouseEvent;
                    }
                }

                break;

            case 2:

                break;
        }

        lastGame = currentGame;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        switch (currentGame){
            case 0:
                setBackground(Color.white);

                break;

            case 1:
                switch (tictactoeState) {
                    case 0:
                        tileM.draw(g2);
                        // System.out.println("running ...");
                        break;
                    case 1:
                        setBackground(Color.RED);

                        gameU.drawCenteredString(g2, "RED WINS ;)", "Montserrat", 60);

                        backButton.setBounds(buttonOffsetX, screenHeight/2 + tileSize/2,2*tileSize, tileSize/2);
                        backButton.setVisible(true);
                        playAgainButton.setVisible(true);
                        break;
                    case 2:
                        setBackground(Color.blue);
                        // System.out.println("blue");
                        gameU.drawCenteredString(g2, "BLUE WINS ;)", "Montserrat", 60);

                        backButton.setBounds(buttonOffsetX, screenHeight/2 + tileSize/2,2*tileSize, tileSize/2);
                        backButton.setVisible(true);
                        playAgainButton.setVisible(true);
                        break;
                    case 3:
                        setBackground(Color.GRAY);
                        // System.out.println("----");
                        gameU.drawCenteredString(g2, "Unentschieden :-)", "Montserrat", 60);
                        backButton.setBounds(buttonOffsetX, screenHeight/2 + tileSize/2,2*tileSize, tileSize/2);
                        backButton.setVisible(true);
                        playAgainButton.setVisible(true);
                        break;
                }

                break;

                case 2:
                    tileM.draw(g2);
                    break;

        }
    }

    public Point cordsToTile(int x, int y) {
        int tileX = Math.floorDiv(x, tileSize);
        int tileY = Math.floorDiv(y, tileSize);
        return new Point(tileX, tileY);
    }

    // Method to restart the game
    private void restartGames() {
        tictactoeState = 0;
        currentPlayer = 1;
        playAgainButton.setVisible(false);
        backButton.setVisible(false);
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

    public void setScreenSizeTiles(int x, int y, int tileScale){
        this.maxScreenCol = x;
        this.getMaxScreenRow = y;
        this.scale = tileScale;

        tileSize = originalTileSize * scale;
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * getMaxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        revalidate();
        repaint();

        parentFrame.pack();
    }
}


