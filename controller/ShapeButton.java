package Controller;

import Model.Bezier;
import Model.Ellipse;
import Model.Line;
import Model.Polyline;
import View.Main;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeButton extends JButton implements ActionListener {
    Shape shape = null;
    Queue q1 = null;
    String shapeName;
    MainFrame mn = null;

    public ShapeButton(Queue q, String shapeName, MainFrame mn) {
        this.q1 = q;
        this.shapeName = shapeName;
        this.addActionListener(this);
        this.mn = mn;
        switch (shapeName) {
            case "Ellipse":
                this.setIcon(new ImageIcon("src/Images/ellipse.png"));
                break;
            case "Polyline":
                this.setIcon(new ImageIcon("src/Images/polyline.png"));
                break;
            case "Polygon":
                this.setIcon(new ImageIcon("src/Images/polygon.png"));
                break;
            case "Bezier":
                this.setIcon(new ImageIcon("src/Images/bezier.png"));
                break;
            case "Rectangle":
                this.setIcon(new ImageIcon("src/Images/rectangle.png"));
                break;
            case "Line":
                this.setIcon(new ImageIcon("src/Images/line.png"));
                break;

        }
    }
    public void actionPerformed(ActionEvent e){
        switch (shapeName) {
            case "Rectangle":
                mn.activeInstrument = new EmptyInstrument(mn);
                q1.add(new Model.Rectangle(mn.getGlobalColor()));
                break;
            case "Line":
                mn.activeInstrument = new EmptyInstrument(mn);
                q1.add(new Line(mn.getGlobalColor()));
                break;
            case "Bezier":
                mn.activeInstrument = new EmptyInstrument(mn);
                q1.add(new Bezier(mn.getGlobalColor()));
                break;
            case "Polyline":
                mn.activeInstrument = new EmptyInstrument(mn);
                q1.add(new Polyline(mn.getGlobalColor()));
                break;
            case "Polygon":
                mn.activeInstrument = new EmptyInstrument(mn);
                q1.add(new Model.Polygon(mn.getGlobalColor()));
                break;
            case "Ellipse":
                mn.activeInstrument = new EmptyInstrument(mn);
                q1.add(new Ellipse(mn.getGlobalColor()));
                break;
        }
    }
}

