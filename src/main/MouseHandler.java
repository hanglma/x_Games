package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public MouseEvent mouseClicked;

    @Override
    public void mouseClicked(MouseEvent e)
    {
        mouseClicked = e;

        // System.out.println( "X-Wert: " + e.getX() + " Y-Wert: " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
            // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e)
    {
            // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

}



