package Controller;

import Model.Clickable;
import Model.Point;
import Model.Shape;
import View.MainFrame;

import java.util.ArrayList;

public class Hand extends Instrument implements Clickable {

    public MainFrame mn = null;
    int mode = 0;

    Point<Double> ha = null;
    Point<Double> hb = null;

    public Hand(MainFrame mn) {this.mn = mn;}

    @Override
    public void setFirstCordinate(int x, int y) {
        if (mode == 0) {
            ha = Transformator.FSTW(new Point<Integer>(x, y));
            mode = 1;
        } else if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            mn.setShiftx(mn.getShiftx() + (hb.GetX() - ha.GetX()));
            mn.setShifty(mn.getShifty() + (hb.GetY() - ha.GetY()));

            for (int i = 0; i < mn.quaue.queue.size(); i++) {
                Shape shape = mn.quaue.queue.get(i);
                for (int j = 0; j < shape.coords.size(); j++) {
                    shape.coords.set(j, new Point<Double>(shape.coords.get(j).GetX() + mn.getShiftx()/mn.getMult(),
                            shape.coords.get(j).GetY() + mn.getShifty()/mn.getMult()));
                }
                shape.coordsCopy = (ArrayList<Point<Double>>) shape.coords.clone();
                shape.angle = 0;
            }
            mn.setShifty(0);
            mn.setShiftx(0);
            //mn.setMult(0);
            mn.activeInstrument = new EmptyInstrument(mn);
        }
    }

    @Override
    public void setNextCordinate(int x, int y) {
        if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            mn.setShiftx(mn.getShiftx() + (hb.GetX() - ha.GetX()));
            mn.setShifty(mn.getShifty() + (hb.GetY() - ha.GetY()));

            mn.canvas.paintToMemory();
            //mn.canvas.repaint();
        }
    }

    @Override
    public void setLastCordinate(int x, int y) {
        for (int i = 0; i < mn.quaue.queue.size(); i++) {
            Shape shape = mn.quaue.queue.get(i);
            for (int j = 0; j < shape.coords.size(); j++) {
                shape.coords.set(j, new Point<Double>(shape.coords.get(j).GetX() + mn.getShiftx()/mn.getMult(),
                        shape.coords.get(j).GetY() + mn.getShifty()/mn.getMult()));
            }
            shape.coordsCopy = (ArrayList<Point<Double>>) shape.coords.clone();
        }
        mn.setShifty(0);
        mn.setShiftx(0);

        mn.activeInstrument = new EmptyInstrument(mn);
    }
}
