package Model;

import Controller.Transformator;

import java.awt.*;
import java.io.Serializable;

public class Polygon extends Polyline implements Serializable {


    public Polygon(Color color)  {
        super(color);
    }

    @Override
    public boolean into(int x, int y) {
        int i, j = 0;
        boolean result = false;
        int[] verty = new int[coords.size()];
        int[] vertx = new int[coords.size()];

        for (i = 0; i < coords.size(); i++) {
            vertx[i] = Transformator.FWTS(coords.get(i)).GetX();
            verty[i] = Transformator.FWTS(coords.get(i)).GetY();
        }

        for (i = 0, j = coords.size()-1; i < coords.size(); j = i++) {
            if ( ((verty[i]>y) != (verty[j]>y)) &&
                    (x < (vertx[j]-vertx[i]) * (y-verty[i]) / (verty[j]-verty[i]) + vertx[i]) )
                result = !result;
        }
        return result;

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(contourColor);
        if (mode == 1) {
            Point<Double> pw = coords.get(0);
            for (int i = 1; i < coords.size(); i++) {
                Point<Double> pt = coords.get(i);
                g.drawLine(Transformator.FWTS(pw).GetX(), Transformator.FWTS(pw).GetY(),
                        Transformator.FWTS(pt).GetX(), Transformator.FWTS(pt).GetY());
                pw = pt;
            }
        }
        if (mode >= 5) {
            if ((mode == 5) || (mode == 10)) {
                rotate(angle);
            }
            if (!isFill) {
                int[] x = new int[coords.size()];
                int[] y = new int[coords.size()];
                for (int i = 0; i < coords.size(); i++) {
                    x[i] = Transformator.FWTS(coords.get(i)).x;
                    y[i] = Transformator.FWTS(coords.get(i)).y;
                }
                g.drawPolygon(x, y, coords.size());
            } else {
                g.setColor(fillColor);
                int[] x = new int[coords.size()];
                int[] y = new int[coords.size()];
                for (int i = 0; i < coords.size(); i++) {
                    x[i] = Transformator.FWTS(coords.get(i)).x;
                    y[i] = Transformator.FWTS(coords.get(i)).y;
                }
                g.fillPolygon(x, y, coords.size());
                g.setColor(contourColor);
                g.drawPolygon(x, y, coords.size());
            }
        }
    }
}
