package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainFrame extends JFrame implements MouseMotionListener {

    public Canvas canvas = null;
    public Queue quaue = null;
    public Instrument activeInstrument = null;
    public Transformator tsf = new Transformator();
    public String savename = null;

    Color GlobalColor = Color.BLACK;

    public void setMult(double mult) {
       tsf.setMult(mult);
    }
    public double getMult() {return tsf.getMult();}


    public void setShiftx(double shiftx) {tsf.setShiftx(shiftx);}
    public void setShifty(double shifty) {tsf.setShifty(shifty);}

    public double getShiftx() {return tsf.getShiftx();}
    public double getShifty() {return tsf.getShifty();}


    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.PINK);
        g.fillRect(5, 205, 110, 2);
    }

    public void setGlobalColor(Color color) {this.GlobalColor = color;}
    public Color getGlobalColor() {return this.GlobalColor;}

    public MainFrame() {
        super("BETA 0.6");

        this.setBounds(0,0,1000,600);
        this.setLayout(null);

        activeInstrument = new EmptyInstrument(this);


        quaue = new Queue();
        canvas = new Canvas(quaue, this);
        this.add(canvas);
        canvas.setVisible(true);
        canvas.paintToMemory();
        canvas.repaint();



        ShapeButton button_rect = new ShapeButton(quaue, "Rectangle", this);
        button_rect.setBounds(5, 20, 50, 50);
        this.add(button_rect);

        ShapeButton button_line = new ShapeButton(quaue, "Line", this);
        button_line.setBounds(65, 20, 50, 50);
        this.add(button_line);

        ShapeButton button_pline = new ShapeButton(quaue, "Polyline", this);
        button_pline.setBounds(5, 75, 50, 50);
        this.add(button_pline);

        ShapeButton button_ellipse = new ShapeButton(quaue, "Ellipse", this);
        button_ellipse.setBounds(65, 75, 50, 50);
        this.add(button_ellipse);

        ShapeButton button_polygon = new ShapeButton(quaue, "Polygon", this);
        button_polygon.setBounds(5, 130, 50, 50);
        this.add(button_polygon);

        ShapeButton button_bezier = new ShapeButton(quaue, "Bezier", this);
        button_bezier.setBounds(65, 130, 50, 50);
        this.add(button_bezier);

        InstrumentButton button_rotate = new InstrumentButton(quaue, "Rotate", this);
        button_rotate.setBounds(5, 190, 50, 50);
        this.add(button_rotate);

        InstrumentButton button_shift = new InstrumentButton(quaue, "Shift", this);
        button_shift.setBounds(65, 190, 50,50);
        this.add(button_shift);

        InstrumentButton button_magnifer = new InstrumentButton(quaue, "Magnifer", this);
        button_magnifer.setBounds(5, 245, 50, 50);
        this.add(button_magnifer);

        //InstrumentButton button_clear = new InstrumentButton(quaue, "Clear", this);
        //button_clear.setBounds(65, 245, 50, 50);
       // this.add(button_clear);

        InstrumentButton button_select = new InstrumentButton(quaue, "Select", this);
        button_select.setBounds(65, 245, 50, 50);
        this.add(button_select);

        InstrumentButton button_colorChooser = new InstrumentButton(quaue, "ColorChooser", this);
        button_colorChooser.setBounds(5, 370, 50, 50);
        this.add(button_colorChooser);

        InstrumentButton button_fill = new InstrumentButton(quaue, "Fill", this);
        button_fill.setBounds(65, 370, 50, 50);
        this.add(button_fill);

        InstrumentButton button_save = new InstrumentButton(quaue, "Save", this);
        button_save.setBounds(750, 20, 100, 50);
        this.add(button_save);

        InstrumentButton button_hand = new InstrumentButton(quaue, "Hand", this);
        button_hand.setBounds(6, 300, 50, 50);
        this.add(button_hand);

        InstrumentButton serialerial = new InstrumentButton(quaue, "Serial", this);
        serialerial.setBounds(860, 20, 100, 50);
        this.add(serialerial);

        InstrumentButton button_delete = new InstrumentButton(quaue, "Delete", this);
        button_delete.setBounds(750, 200, 100, 50);
        this.add(button_delete);



        JPanel jp = new JPanel();
        this.setBounds(0,0,1000,600);
        this.setLayout(null);
        jp.setVisible(true);
        //jp.setBackground(Color.GRAY);
        jp.setBounds(750,100,200,500);
        jp.add(quaue.list);
        //quaue.list.setBounds(750, 100, 150, 100);
        this.add(jp);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        canvas.paintToMemory();
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

}
