package Controller;

import View.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Saver extends JFrame{
    private JPanel panel1;
    private JButton saveButton;
    private JTextField textField1;
    public MainFrame mn = null;

    public Saver(MainFrame mn) {
        super("Save file");
        this.mn = mn;
        setContentPane(panel1);
        textField1.setText("Untitled");
        setVisible(true);
        setBounds(200, 200, 320, 100);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();

                mn.quaue.activObject = null;
                mn.canvas.paintToMemory();
                mn.canvas.repaint();
                if (name != null) {
                    try {
                        //String test = "untitled";
                        ImageIO.write((BufferedImage) mn.canvas.img, "png", new File("Saves/" + name + ".png"));
                        System.out.println("Success!");
                    } catch (IOException e1) {
// TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    dispose();
                } else {
                    try {
                        //String test = "untitled";
                        ImageIO.write((BufferedImage) mn.canvas.img, "png", new File("Saves/Untitled.png"));
                        System.out.println("Success!");
                    } catch (IOException e1) {
// TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    dispose();
                }
            }
        });

    }


}
