package Controller;

import View.MainFrame;
import jdk.jfr.Label;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class InstrumentButton extends JButton implements ActionListener {
    Shape shape = null;
    String commandName;
    Queue q1 = null;
    MainFrame mn = null;

    public InstrumentButton(Queue q1, String commandName, MainFrame mn) {
        this.commandName = commandName;
        this.mn = mn;
        this.q1 = q1;
        this.addActionListener(this);
        switch (commandName) {
            case "Rotate":
                this.setIcon(new ImageIcon("src/Images/angle.png"));
                break;
            case "Shift":
                this.setIcon(new ImageIcon("src/Images/shift.png"));
                break;
            case "Magnifer":
                this.setIcon(new ImageIcon("src/Images/magnifer.png"));
                break;
            case "Select":
                this.setIcon(new ImageIcon("src/Images/select.png"));
                break;
            case "ColorChooser":
                this.setIcon(new ImageIcon("src/Images/lc.png"));
                break;
            case "Fill":
                this.setIcon(new ImageIcon("src/Images/fill.png"));
                break;
            case "Hand":
                this.setIcon(new ImageIcon("src/Images/hand.png"));
                break;
            case "Save":
                this.setText("Save as .png");
                break;
                //this.set
            case "Serial":
                this.setText("Serialize");
                break;
            case "Delete":
                this.setText("Delete");
                break;

        }
    }


    public void actionPerformed(ActionEvent e) {
        switch (commandName) {
            case "Rotate":
                q1.getComponenetObject().setMode(10);
                Rotate rotate = new Rotate(mn);
                mn.activeInstrument = rotate;
                q1.getComponenetObject().calculateCenter();
                break;
            case "Shift":
                q1.getComponenetObject().setMode(11);
                Move move = new Move(mn);
                mn.activeInstrument = move;
                q1.getComponenetObject().calculateCenter();
                break;
            case "Select":
                Selector selector = new Selector(mn);
                mn.activeInstrument = selector;
                break;
            case "ColorChooser":
                Color color = JColorChooser.showDialog(new JFrame(), "Palette", Color.white);
                if (q1.activObject != null) {
                    if (color != null) {
                        q1.activObject.setCurrentContourColor(color);
                    }
                }
                mn.setGlobalColor(color);

                break;
            case "Fill":
                mn.quaue.activObject.isFill = true;
                Color color_ = JColorChooser.showDialog(new JFrame(), "Palette", Color.white);
                if (q1.activObject  != null) {
                    if (color_ != null) {
                        q1.activObject.setCurrentFillColor(color_);
                    }
                }
                break;

            case "Save":
                Saver saver = new Saver(mn);
                break;


            case "Magnifer":
                mn.quaue.activObject.coordsCopy = (ArrayList<Model.Point<Double>>) mn.quaue.activObject.coords.clone();
                Magnifer magnifer = new Magnifer(mn);
                mn.activeInstrument = magnifer;
                //q1.getComponenetObject().calculateCenter();
                break;
            case "Clear":
                mn.quaue.activObject.coords.set(0, new Model.Point<Double>(0.0, 0.0));
                mn.quaue.activObject.coordsCopy = (ArrayList<Model.Point<Double>>) mn.quaue.activObject.coords.clone();
                System.out.println("LOL");
                break;
            case "Hand":
                Hand hand = new Hand(mn);
                mn.activeInstrument = hand;
                //mn.quaue.activObject = null;
                mn.canvas.paintToMemory();
                mn.canvas.repaint();
                break;
            case "Serial":
                Serialize serialize = new Serialize(mn);
                break;
            case "Delete":
                Remover remover = new Remover(mn);
                break;
        }
    }
}
