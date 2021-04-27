package View;

import Controller.EmptyInstrument;
import Controller.Queue;
import Model.Clickable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {

    Queue q = null;
    public Image img = null;
    public Graphics g1;
    MainFrame mn = null;

    public Canvas(Queue q, MainFrame mn) {
        super();
        this.q = q;
        this.mn = mn;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setBounds(130,20,600,500);
        img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g1 = img.getGraphics();
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                mn.activeInstrument.mouseWheelMoved(e);
                //e.getPreciseWheelRotation();
            }

        });

    }


    public void paintToMemory() {
        g1.setColor(Color.WHITE);
        Rectangle r = this.getBounds();
        g1.fillRect(0,0,(int)r.getWidth(),(int)r.getHeight());
        q.paintQuaue(g1);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        g.drawImage(img, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e) {
        if (!(mn.activeInstrument instanceof EmptyInstrument)) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                mn.activeInstrument.setLastCordinate(e.getX(), e.getY());
                //paintToMemory();
                repaint();
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                mn.activeInstrument.setFirstCordinate(e.getX(), e.getY());
            }
            repaint();
        } else {
            Model.Shape shape = q.getComponenetObject();
            if (e.getButton() == MouseEvent.BUTTON3) {
                shape.setLastCordinate(e.getX(), e.getY());
                paintToMemory();
                repaint();
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                shape.setFirstCordinate(e.getX(), e.getY());
            }
        }
    }

    public void mousePressed(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e){

    }
    public void mouseDragged(MouseEvent e){

    }

    public void mouseMoved(MouseEvent e){

        if (!(mn.activeInstrument instanceof EmptyInstrument)) {
            mn.activeInstrument.setNextCordinate(e.getX(), e.getY());
        }

        else {
            Model.Shape shape = q.getComponenetObject();
            if (shape != null) {
                shape.setNextCordinate(e.getX(), e.getY());

            }
        }
        paintToMemory();
        repaint();
    }


}


