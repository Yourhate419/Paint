package Model;

import Controller.Serialize;
import Controller.Transformator;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Rectangle extends Polygon implements Serializable {

    Point<Double> hc;
    Point<Double> hd;

    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void setFirstCordinate(int x, int y) {
        if (mode == 0) {
            ha = Transformator.FSTW(new Point<Integer>(x, y));
            coords.add(ha);
            coords.add(ha);
            coords.add(ha);
            coords.add(ha);
            mode = 1;
        } else if (mode == 1) {
            coords.set(2, Transformator.FSTW(new Point<Integer>(x, y)));
            mode = 5;
            calculateCenter();
        }

    }

    @Override
    public void setNextCordinate(int x, int y) {
        if ((coords.size() >= 1) && (mode == 1)) {
            coords.set(2, Transformator.FSTW(new Point<Integer>(x, y)));
        }
    }

    @Override
    public void setLastCordinate(int x, int y) {
        if ((coords.size() >= 1) && (mode == 1)) {
            coords.set(2, Transformator.FSTW(new Point<Integer>(x, y)));
            mode = 5;
            calculateCenter();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(contourColor);
        if (mode > 0) {

            int x[] = new int[4];
            int y[] = new int[4];

            if ((mode == 10) || (mode == 5)) {
                rotate(angle);
            }


            if ((mode == 1) ) {



                x[0] = Transformator.FWTS(coords.get(0)).GetX();
                y[0] = Transformator.FWTS(coords.get(0)).GetY();

                x[1] = Transformator.FWTS(coords.get(2)).GetX();
                y[1] = Transformator.FWTS(coords.get(0)).GetY();

                coords.set(1, Transformator.FSTW(new Point<Integer>(x[1], y[1])));

                x[2] = Transformator.FWTS(coords.get(2)).GetX();
                y[2] = Transformator.FWTS(coords.get(2)).GetY();

                x[3] = Transformator.FWTS(coords.get(0)).GetX();
                y[3] = Transformator.FWTS(coords.get(2)).GetY();

                coords.set(3, Transformator.FSTW(new Point<Integer>(x[3], y[3])));
            }

            if (mode >= 5) {

                for (int i = 0; i < 4; i++) {
                    x[i] = Transformator.FWTS(coords.get(i)).GetX();
                    y[i] = Transformator.FWTS(coords.get(i)).GetY();

                }
            }

            if (!isFill) {
                g.drawPolygon(x, y, 4);
            } else {
                g.setColor(fillColor);
                g.fillPolygon(x, y, 4);
                g.setColor(contourColor);
                g.drawPolygon(x, y, 4);

            }
        }
    }
}
