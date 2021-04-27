package Controller;

import Model.Clickable;
import Model.Point;
import View.Main;
import View.MainFrame;

import java.util.ArrayList;

public class Rotate extends Instrument implements Clickable {

    public Rotate(MainFrame mn) {this.mn = mn;}
    int mode = 0;

    @Override
    public void setFirstCordinate(int x, int y) {
        if (mode == 0) {
            mn.quaue.getComponenetObject().coordsCopy = (ArrayList<Point<Double>>) mn.quaue.getComponenetObject().coords.clone();
            mn.quaue.getComponenetObject().calculateCenter();
            mn.quaue.getComponenetObject().ha = Transformator.FSTW(new Point<Integer>(x, y));
            mode = 1;
        }

        else if (mode == 1) {
            mn.quaue.getComponenetObject().rotate(mn.quaue.activObject.angle);
            mn.quaue.getComponenetObject().setMode(5);
            //mn.quaue.activObject.angle = 0;
            mode = 0;
            mn.activeInstrument = new EmptyInstrument(mn);
        }
    }

    @Override
    public void setNextCordinate(int x, int y) {
        if (mode == 1) {
            mn.quaue.getComponenetObject().hb = Transformator.FSTW(new Point<Integer>(x, y));
            mn.quaue.getComponenetObject().angle = Transformator.calculateAngle(mn.quaue.getComponenetObject().center,
                    mn.quaue.getComponenetObject().ha, mn.quaue.getComponenetObject().hb);
        }
    }

    @Override
    public void setLastCordinate(int x, int y) {
        mn.activeInstrument = new EmptyInstrument(mn);
    }
}
