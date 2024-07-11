package main;

import java.awt.*;

public class gameUI {

    GamePanel gp;

    public gameUI(GamePanel gp){
        this.gp = gp;
    }


    public void drawCenteredString(Graphics2D g2, String text, String fontName, int size){

        g2.setFont(new Font(fontName, Font.BOLD, size));
        g2.setColor(Color.BLACK);
        // Get the FontMetrics for the current font
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        // Calculate the position for the string to be centered horizontally
        int x = (gp.screenWidth - metrics.stringWidth(text)) / 2;
        // Set the y position for the text
        int y = (gp.screenHeight - metrics.getHeight()) / 2 + metrics.getAscent() - gp.tileSize;

        // Draw the string on the panel
        g2.drawString(text, x, y);
    }
}
