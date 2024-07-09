package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;

public class MouseHandler implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e)
    {

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
        System.out.println( "X-Wert: " + e.getX() + " Y-Wert: " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

}



