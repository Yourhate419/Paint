package Controller;

import Model.Point;

import java.io.Serializable;
import java.util.ArrayList;

public class Transformator implements Serializable {
    static  public double mult = 1;
    static  public double shiftx = 0;
    static  public double shifty = 0;

    public void setMult(double mult) {this.mult = mult;}
    public double getMult() {return this.mult;}

    public void setShiftx(double shiftx) {this.shiftx = shiftx*mult;}
    public void setShifty(double shifty) {this.shifty = shifty*mult;}

    public double getShiftx() {return this.shiftx;}
    public double getShifty() {return this.shifty;}

    public static Point<Integer> FWTS(Point<Double> p) {
        double x = p.GetX();
        double y = p.GetY();
        int xs = (int)(x*mult + shiftx);
        int ys = (int)(y*mult + shifty);
        Point<Integer> result = new Point<Integer>(xs, ys);
        return result;
    }

    public static Point<Double> FSTW(Point<Integer> p) {
        int x = p.GetX();
        int y = p.GetY();
        double xs = x/mult - shiftx;
        double ys = y/mult - shifty;
        Point<Double> result = new Point<Double>(xs, ys);
        return result;
    }
    public static double calculateAngle(Point<Double> center, Point<Double> secondPoint, Point<Double> thirdPoint) {

        double alpha1 = Math.atan2(secondPoint.GetY() - center.GetY(), secondPoint.GetX() - center.GetX());
        double alpha2 = Math.atan2(thirdPoint.GetY() - center.GetY(), thirdPoint.GetX() - center.GetX());

        double angle = alpha2 - alpha1;
        return angle;
    }

    public static Point<Integer> min(ArrayList<Point<Double>> coords) {
        double x = coords.get(0).GetX();
        double y = coords.get(0).GetY();

        for (int i = 0; i < coords.size(); i++) {
            if (x < coords.get(i).GetX())
                x = coords.get(i).GetX();
            if (y < coords.get(i).GetY())
                y = coords.get(i).GetY();
        }

        return Transformator.FWTS(new Point<Double>(x, y));
    }

    public static Point<Integer> max(ArrayList<Point<Double>> coords) {
        double x = coords.get(0).GetX();
        double y = coords.get(0).GetY();

        for (int i = 0; i < coords.size(); i++) {
            if (x > coords.get(i).GetX())
                x = coords.get(i).GetX();
            if (y > coords.get(i).GetY())
                y = coords.get(i).GetY();
        }

        return Transformator.FWTS(new Point<Double>(x, y));
    }
}
