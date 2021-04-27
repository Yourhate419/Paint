package Model;

import Controller.Serialize;
import Controller.Transformator;
import View.Main;

import java.awt.*;
import java.io.Serializable;

public class Line extends Polyline implements Serializable {


    public Line(Color color) {
        super(color);
    }

    @Override
    public void setFirstCordinate(int x, int y) {
        if (mode == 0) {
            ha = Transformator.FSTW(new Point<Integer>(x, y));
            coords.add(ha);
            coords.add(ha);
            mode = 1;
        } else if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            coords.set(1, hb);
            mode = 5;
        }
    }

    @Override
    public void setLastCordinate(int x, int y) {
        if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            coords.set(1, hb);
            mode = 5;
        }
    }

    @Override
    public boolean into(int x, int y) {


        int x_ = Math.max(x, Transformator.FWTS(coords.get(0)).x) - Math.min(x, Transformator.FWTS(coords.get(0)).x);
        int y_ = Math.max(y, Transformator.FWTS(coords.get(0)).y) - Math.min(y, Transformator.FWTS(coords.get(0)).y);

        int _x = Math.max(x, Transformator.FWTS(coords.get(1)).x) - Math.min(x, Transformator.FWTS(coords.get(1)).x);
        int _y = Math.max(y, Transformator.FWTS(coords.get(1)).y) - Math.min(y, Transformator.FWTS(coords.get(1)).y);


        int X = Math.max(Transformator.FWTS(coords.get(1)).x, Transformator.FWTS(coords.get(0)).x)
                - Math.min(Transformator.FWTS(coords.get(1)).x, Transformator.FWTS(coords.get(0)).x);
        int Y = Math.max(Transformator.FWTS(coords.get(1)).y, Transformator.FWTS(coords.get(0)).y)
                - Math.min(Transformator.FWTS(coords.get(1)).y, Transformator.FWTS(coords.get(0)).y);

        System.out.println(Math.sqrt(X*X + Y*Y));
        System.out.println(Math.sqrt(x_*x_ + y_*y_) + Math.sqrt(_x*_x + _y*_y));

        if (Math.sqrt(X*X + Y*Y) + 1 > Math.sqrt(x_*x_ + y_*y_) + Math.sqrt(_x*_x + _y*_y)) {
            System.out.println("1");
            return true;

        }

        if (((coords.get(0).x + 5 > x)&&(coords.get(0).x - 5 < x))
                &&((coords.get(0).y + 5 > x)&&(coords.get(0).y - 5 < y))) {
            return true;
        }

        if (((coords.get(1).x + 5 > x)&&(coords.get(1).x - 5 < x))&&((coords.get(1).y + 5 > x)
                &&(coords.get(1).y - 5 < y))) {
            return true;
        }

        System.out.println("0");
        return false;

    }

    @Override
    public void setNextCordinate(int x, int y) {
        if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            coords.set(1, hb);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (mode > 0) {
            if ((mode == 5) || (mode == 10)) {
                rotate(angle);
            }

            g.setColor(contourColor);

            g.drawLine(Transformator.FWTS(coords.get(0)).x, Transformator.FWTS(coords.get(0)).y,
                    Transformator.FWTS(coords.get(1)).x,Transformator.FWTS(coords.get(1)).y);
        }
    }
}
