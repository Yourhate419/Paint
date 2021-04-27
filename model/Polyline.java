package Model;

import Controller.Serialize;
import Controller.Transformator;

import java.awt.*;
import java.io.Serializable;

public class Polyline extends Shape implements Serializable {


    static final long serialVersionUID = 2585485592490667269L;

    public Polyline(Color color) {
        this.setCurrentContourColor(color);
    }

    @Override
    public void setFirstCordinate(int x, int y) {
        if ((mode == 0) || (mode == 1)) {
            mode = 1;
            Point<Double> pw = Transformator.FSTW(new Point<Integer>(x, y));
            if (coords.size() < 2) {
                coords.add(pw);
                coordsCopy.add(pw);
            }
            coords.add(pw);
            coordsCopy.add(pw);
        }
    }

    @Override
    public boolean into(int x, int y) {
        for (int i = 0; i < coords.size()-1; i++) {
            int x_ = Math.max(x, Transformator.FWTS(coords.get(i)).x) - Math.min(x, Transformator.FWTS(coords.get(i)).x);
            int y_ = Math.max(y, Transformator.FWTS(coords.get(i)).y) - Math.min(y, Transformator.FWTS(coords.get(i)).y);

            int _x = Math.max(x, Transformator.FWTS(coords.get(i+1)).x) - Math.min(x, Transformator.FWTS(coords.get(i+1)).x);
            int _y = Math.max(y, Transformator.FWTS(coords.get(i+1)).y) - Math.min(y, Transformator.FWTS(coords.get(i+1)).y);


            int X = Math.max(Transformator.FWTS(coords.get(i+1)).x, Transformator.FWTS(coords.get(i)).x)
                    - Math.min(Transformator.FWTS(coords.get(i+1)).x, Transformator.FWTS(coords.get(i)).x);
            int Y = Math.max(Transformator.FWTS(coords.get(i+1)).y, Transformator.FWTS(coords.get(i)).y)
                    - Math.min(Transformator.FWTS(coords.get(i+1)).y, Transformator.FWTS(coords.get(i)).y);

            System.out.println(Math.sqrt(X*X + Y*Y));
            System.out.println(Math.sqrt(x_*x_ + y_*y_) + Math.sqrt(_x*_x + _y*_y));

            if (Math.sqrt(X*X + Y*Y) + 1 > Math.sqrt(x_*x_ + y_*y_) + Math.sqrt(_x*_x + _y*_y)) {
                System.out.println("1");
                return true;

            }

            if (((coords.get(i).x + 5 > x)&&(coords.get(i).x - 5 < x))
                    &&((coords.get(i).y + 5 > x)&&(coords.get(i).y - 5 < y))) {
                return true;
            }

            if (((coords.get(i+1).x + 5 > x)&&(coords.get(i+1).x - 5 < x))&&((coords.get(i+1).y + 5 > x)
                    &&(coords.get(i+1).y - 5 < y))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawBorder(Graphics g) {

        g.setColor(Color.GREEN);

        if (coords.size() > 1) {

            int x1 = Transformator.max(coords).x;
            int y1 = Transformator.max(coords).y;

            int x2 = Transformator.min(coords).x;
            int y2 = Transformator.min(coords).y;

            g.drawRect(Math.min(x1, x2) - 10, Math.min(y1, y2) - 10, Math.abs(x1 - x2) + 20, Math.abs(y1 - y2) + 20);
        }
    }


    @Override
    public void setNextCordinate(int x, int y) {
        if ((coords.size() >= 1) && (mode == 1)) {
            coords.set(coords.size() - 1, Transformator.FSTW(new Point<Integer>(x, y)));
        }
    }

    @Override
    public void setLastCordinate(int x, int y) {
        if (mode == 1) {
            setMode(5); //mode 5 - ПКМ
            calculateCenter();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(contourColor);

        if (mode > 0) {
            if ((mode == 5)||(mode == 10)) {
                rotate(angle);
            }

            Point<Double> pw = coords.get(0);
            for (int i = 1; i < coords.size(); i++) {
                Point<Double> pt = coords.get(i);
                g.drawLine(Transformator.FWTS(pw).GetX(), Transformator.FWTS(pw).GetY(),
                        Transformator.FWTS(pt).GetX(), Transformator.FWTS(pt).GetY());
                pw = pt;
            }
        }
    }

}
