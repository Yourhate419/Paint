package View;

import Controller.InstrumentButton;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {

        MainFrame a = new MainFrame();
        a.setVisible(true);
        a.setBounds(new Rectangle(5, 5, 1000, 650));
        a.setDefaultCloseOperation(a.EXIT_ON_CLOSE);

        Model.Shape shape = null;
        Model.Shape shape1 = null;

        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            //int t = oin.readInt();
            //System.out.println(t);

            try {
                do {
                    shape = (Model.Shape) oin.readObject();
                    a.quaue.add(shape);
                } while (shape != null);
            } catch (ClassNotFoundException i) {
                System.out.println("FALL");
            }
            fis.close();

        } catch (IOException ioe) {
            System.out.println("Trouble*reading from the file: " + ioe.getMessage());
        }
    }

}
