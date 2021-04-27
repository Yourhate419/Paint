package Model;

import Controller.Transformator;
import View.MainFrame;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Ellipse extends Polygon implements Serializable {

    //public ArrayList<Point<Double>> arrayList = new ArrayList<Point<Double>>();

    int count = 0;
    //int count2 = 0;

   // ArrayList<Point<Double>> coordsCopy1 = new ArrayList<Point<Double>>();

    ArrayList<Point<Double>> coords1 = new ArrayList<Point<Double>>();
    ArrayList<Point<Double>> coords2 = new ArrayList<Point<Double>>();
    ArrayList<Point<Double>> coords3 = new ArrayList<Point<Double>>();
    ArrayList<Point<Double>> coords4 = new ArrayList<Point<Double>>();


    public Ellipse(Color color) {
        super(color);
    }




    @Override
    public void setFirstCordinate(int x, int y) {
        if (mode == 0) {
            ha = Transformator.FSTW(new Point<Integer>(x, y));
            Point<Double> haa = new Point<Double>(ha.GetX(), ha.GetY());
            arrayList.add(haa);
            arrayList.add(haa);
            mode = 1;
        } else if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            Point<Double> haa = new Point<Double>(hb.GetX(), hb.GetY());
            arrayList.set(1, haa);
            mode = 5;
        }
    }

    @Override
    public void setNextCordinate(int x, int y) {
        if (mode == 1) {
            hb = Transformator.FSTW(new Point<Integer>(x, y));
            Point<Double> haa = new Point<Double>(hb.GetX(),  hb.GetY());
            arrayList.set(1, haa);
        }
    }

    @Override
    public void setLastCordinate(int x, int y) {
        hb = Transformator.FSTW(new Point<Integer>(x, y));
        Point<Double> haa = new Point<Double>(hb.GetX(), hb.GetY());
        arrayList.set(1, haa);
        mode = 5;
    }


    void cdadd(int x, int y, int x_, int y_) {
       // coords1.add(coords1.size(),new Point<Double>((double) (x + x_), (double)(y + y_)));
        //coords2.add(coords2.size(),new Point<Double>((double) (x + x_), (double)(y - y_)));
        //coords3.add(coords3.size(),new Point<Double>((double) (x - x_), (double)(y - y_)));
        //coords4.add(coords4.size(),new Point<Double>((double) (x - x_), (double)(y + y_)));
        coords1.add(Transformator.FSTW(new Point<Integer>(x+x_,y+y_ )));
        coords2.add(Transformator.FSTW(new Point<Integer>(x+x_,y-y_ )));
        coords3.add(Transformator.FSTW(new Point<Integer>(x-x_,y-y_ )));
        coords4.add(Transformator.FSTW(new Point<Integer>(x-x_,y+y_ )));
    }

    void CalculateEllipse(int x, int y, int a, int b) {
        boolean af = true;

        int _x = 0;
        int _y = b;
        int a_sqr = a * a;
        int b_sqr = b * b;
        int delta = 4 * b_sqr * ((_x + 1) * (_x + 1)) + a_sqr * ((2 * _y - 1) * (2 * _y - 1)) - 4 * a_sqr * b_sqr; // Функция координат точки (x+1, y-1/2)
        while (a_sqr * (2 * _y - 1) > 2 * b_sqr * (_x + 1)) // Первая часть дуги
        {
            if (af) {
                cdadd(x, y, _x, _y);
            }
            if (count == 3) {
                af = !af;
                count = 0;
            }
            if (delta < 0) // Переход по горизонтали
            {
                _x++;
                delta += 4 * b_sqr * (2 * _x + 3);
            }
            else // Переход по диагонали
            {
                _x++;
                delta = delta - 8 * a_sqr * (_y - 1) + 4 * b_sqr * (2 * _x + 3);
                _y--;
            }
            count++;
        }
        af = true;
        delta = b_sqr * ((2 * _x + 1) * (2 * _x + 1)) + 4 * a_sqr * ((_y + 1) * (_y + 1)) - 4 * a_sqr * b_sqr; // Функция координат точки (x+1/2, y-1)
        while (_y + 1 != 0) // Вторая часть дуги, если не выполняется условие первого цикла, значит выполняется a^2(2y - 1) <= 2b^2(x + 1)
        {
            if (af) {
                cdadd(x, y, _x, _y);

            }


            if (count == 3) {
                af = !af;
                count = 0;
            }
            if (delta < 0) // Переход по вертикали
            {
                _y--;
                delta += 4 * a_sqr * (2 * _y + 3);
            }
            else // Переход по диагонали
            {
                _y--;
                delta = delta - 8 * b_sqr * (_x + 1) + 4 * a_sqr * (2 * _y + 3);
                _x++;
            }
            count++;
        }



            for (int i = 0; i < coords2.size(); i++) {
                coords.add( coords2.get(i));
                //System.out.println("LOL");
            }

            for (int i = coords1.size() - 1, j = 0; i > 0; i--, j++) {
                coords.add(coords1.get(i));
            }

            for (int i = 0; i < coords4.size(); i++) {
                coords.add(coords4.get(i));
            }

            for (int i = coords3.size() - 1, j = 0; i > 0; i--, j++) {
                coords.add(coords3.get(i));
            }





    }

    @Override
    public void paint(Graphics g) {

        //coords.clear();
        g.setColor(contourColor);
        if (mode == 1) {


            coords.clear();
            x_ = Transformator.FWTS(arrayList.get(0)).x;
            y_ = Transformator.FWTS(arrayList.get(0)).y;
            a = Math.abs(Transformator.FWTS(arrayList.get(1)).x - Transformator.FWTS(arrayList.get(0)).x);
            b = Math.abs(Transformator.FWTS(arrayList.get(1)).y - Transformator.FWTS(arrayList.get(0)).y);
            CalculateEllipse(Transformator.FWTS(arrayList.get(0)).x, Transformator.FWTS(arrayList.get(0)).y,
                    Math.abs(Transformator.FWTS(arrayList.get(1)).x - Transformator.FWTS(arrayList.get(0)).x),
                    Math.abs(Transformator.FWTS(arrayList.get(1)).y - Transformator.FWTS(arrayList.get(0)).y));



            int[] x = new int[coords.size()];
            int[] y = new int[coords.size()];
            for (int i = 0; i < coords.size(); i++) {
                x[i] = Transformator.FWTS(coords.get(i)).GetX();
                y[i] = Transformator.FWTS(coords.get(i)).GetY();
            }

            if (isFill) {
                g.setColor(fillColor);
                g.fillPolygon(x, y, coords.size());
            }
            g.setColor(contourColor);
            g.drawPolygon(x, y, coords.size());


            coords1.clear();
            coords2.clear();
            coords3.clear();
            coords4.clear();


        } else if (mode >=5) {

            //coordsCopy = (ArrayList<Point<Double>>) coords.clone();
            //calculateCenter();
            if ((mode == 5) || (mode == 10)) {
                rotate(angle);
            }

            int[] x = new int[coords.size()];
            int[] y = new int[coords.size()];
            for (int i = 0; i < coords.size(); i++) {
                x[i] = Transformator.FWTS(coords.get(i)).GetX();
                y[i] = Transformator.FWTS(coords.get(i)).GetY();
            }

            if (isFill) {
                g.setColor(fillColor);
                g.fillPolygon(x, y, coords.size());
            }
            g.setColor(contourColor);
            g.drawPolygon(x, y, coords.size());


        }
    }
}


