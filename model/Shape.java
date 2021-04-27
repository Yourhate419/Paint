package Model;

import Controller.Transformator;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Shape implements Clickable, Serializable {
    public ArrayList<Point<Double>> coords = new ArrayList<Point<Double>>();
    public ArrayList<Point<Double>> coordsCopy = new ArrayList<Point<Double>>();
    public Point<Double> center;
    public Point<Double> ha = null;
    public Point<Double> hb = null;

    public Transformator t = new Transformator();

    public void setMult(double mult) {
        t.setMult(mult);
    }

    public ArrayList<Point<Double>> arrayList = new ArrayList<Point<Double>>();

    public int mode = 0;

    public double angle = 0;

    public boolean isFill = false;



    public int x_ = 0;
    public int y_ =0;
    public int a = 0;
    public int b = 0;





    protected Color contourColor =  Color.BLACK;
    protected Color fillColor = Color.white;

    public void setCurrentContourColor(Color color) {
        this.contourColor = color;
    }

    public void setCurrentFillColor(Color color) {this.fillColor = color;}

    @Override
    public void setFirstCordinate(int x, int y){

    }
    @Override
    public void setLastCordinate (int x, int y){

    }
    public void paint (Graphics g){

    }

    public void rotate(double angle) {
        if (coordsCopy.size() == coords.size()) {
            if (mode >= 5) {

                for (int i = 0; i < coords.size(); i++) {
                    double x_ = ((coordsCopy.get(i).x - center.x) * Math.cos(angle)
                            - (coordsCopy.get(i).y - center.y) * Math.sin(angle)) + center.x;

                    double y_ = ((coordsCopy.get(i).x - center.x) * Math.sin(angle)
                            + (coordsCopy.get(i).y - center.y) * Math.cos(angle)) + center.y;

                    Point<Double> pu = new Point<Double>(x_, y_);
                    coords.set(i, pu);
                }
            }
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setNextCordinate(int x, int y) {

    }

    public boolean into(int x, int y) {
        return false;
    }

    public void drawBorder(Graphics g) {

    }

    public void drawCenter(Graphics g) {
        g.setColor(Color.PINK);

        if (center != null) {
            g.setColor(Color.PINK);

            for (int i = 0; i < coords.size(); i++) {
                Point<Double> pt = coords.get(i);
                g.drawLine(Transformator.FWTS(pt).x, Transformator.FWTS(pt).y,
                        Transformator.FWTS(center).x, Transformator.FWTS(center).y);
            }

            g.setColor(Color.BLACK);
            g.drawRect(Transformator.FWTS(center).x - 7,
                    Transformator.FWTS(center).y - 7,14, 14);
        }
    }

    public void calculateCenter() {
        if (coords.size() >= 2) {
            double x_ = 0;
            double y_ = 0;
            for (int i = 0; i < coords.size(); i++) {
                x_ += coords.get(i).x;
                y_ += coords.get(i).y;
            }
            center = new Point<Double>(x_ / coords.size(), y_ / coords.size());
        }
    }

}