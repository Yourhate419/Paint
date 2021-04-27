package Controller;

import Model.Clickable;
import Model.Ellipse;
import Model.Point;
import View.MainFrame;

import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Move extends Instrument implements Clickable {

    protected MainFrame mn = null;
    int mode = 0;

    public Move(MainFrame mn) {this.mn = mn;}

    @Override
    public void setLastCordinate(int x, int y) {
        mn.activeInstrument = new EmptyInstrument(mn);
    }

    @Override
    public void setNextCordinate(int x, int y) {
        if (mode == 1) {
                mn.quaue.activObject.hb = Transformator.FSTW(new Point<Integer>(x, y));
                for (int i = 0; i < mn.quaue.activObject.coords.size(); i++) {
                    double x_ = mn.quaue.activObject.hb.GetX() - mn.quaue.activObject.ha.GetX();
                    double y_ = mn.quaue.activObject.hb.GetY() - mn.quaue.activObject.ha.GetY();
                    double cx = mn.quaue.activObject.coordsCopy.get(i).GetX() + x_;
                    System.out.println(x_);
                    double cy = mn.quaue.activObject.coordsCopy.get(i).GetY() + y_;
                    mn.quaue.activObject.coords.set(i, new Point<Double>(cx, cy));
                }
            //mn.quaue.activObject.calculateCenter();
        }
    }

    @Override
    public void setFirstCordinate(int x, int y) {
        if (mode == 0) {
            mn.quaue.getComponenetObject().coordsCopy = (ArrayList<Point<Double>>) mn.quaue.getComponenetObject().coords.clone();
            mn.quaue.activObject.ha = Transformator.FSTW(new Point<Integer>(x, y));
            mode = 1;
        }

        else if (mode == 1) {
            mn.quaue.activObject.mode = 5;
            mn.quaue.activObject.coordsCopy = (ArrayList<Point<Double>>) mn.quaue.activObject.coords.clone();
            //mn.quaue.activObject.rotate(mn.quaue.activObject.angle);
            mn.quaue.activObject.calculateCenter();
            mn.quaue.activObject.angle = 0;
            mode = 0;
            mn.activeInstrument = new EmptyInstrument(mn);
        }
    }

}
