package Controller;

import Model.Clickable;
import View.MainFrame;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Magnifer extends Instrument implements Clickable, MouseWheelListener {

    public MainFrame mn = null;

    double m = 0;

    public Magnifer(MainFrame mn) {this.mn = mn;}


    @Override
    public void setFirstCordinate(int x, int y) {
        System.out.println(m);
    }
    @Override
    public void setLastCordinate(int x, int y) {
        mn.activeInstrument = new EmptyInstrument(mn);
    }
    @Override
    public void setNextCordinate(int x, int y) {

    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        m = e.getPreciseWheelRotation();
        System.out.println(m/10);
        mn.setMult(mn.getMult() + Math.log(Math.exp(m))/100);
        mn.canvas.paintToMemory();
        mn.canvas.repaint();
    }
}
