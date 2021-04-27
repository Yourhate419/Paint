package Model;

import Controller.Transformator;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;


public class Bezier extends Polyline implements Serializable {

    Color contourColor = null;

    public Bezier(Color color) {
        super(color);
        setCurrentContourColor(color);
    }

    @Override
    public void setCurrentContourColor(Color color) {
        this.contourColor = color;
    }

    public Point<Double> CountBesier(double t) {
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();

        for (int i = 0; i < coords.size(); i++) {
            x.add(coords.get(i).x);
            y.add(coords.get(i).y);
        }
        int k;
        for (k = coords.size(); k > 0; k--) {
            for (int i = 0; i < k-1; i++) {
                x.set(i, (1-t)*x.get(i) + t*x.get(i+1));
                y.set(i, (1-t)*y.get(i)+  t*y.get(i+1));

            }
        }
        return new Point<Double>(x.get(0), y.get(0));
    }

    @Override
    public void paint(Graphics g) {
        //g.setColor(Color.PINK);
        super.setCurrentContourColor(new Color(238,130,238, 75));
        super.paint(g);
        g.setColor(contourColor);
        if (mode >= 1) {
            double deltaT = 0.001;
            double t = 0;
            Point<Double> zero = (Point) coords.get(0).Clone();
            while (t < 1) {
                t += deltaT;
                Point<Double> one = CountBesier(t);
                g.drawLine(Transformator.FWTS(zero).x, Transformator.FWTS(zero).y,
                        Transformator.FWTS(one).x, Transformator.FWTS(one).y);
                zero = one;
            }
        }
    }
}
