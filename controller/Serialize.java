package Controller;

import Model.Shape;
import View.MainFrame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize extends Instrument {

    MainFrame mn = null;

    public Serialize(MainFrame mn) {
        this.mn = mn;
        try {


            FileOutputStream fos = new FileOutputStream("temp.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < mn.quaue.queue.size(); i++) {
                oos.writeObject(mn.quaue.queue.get(i));
            }
            oos.close();

            System.out.println("SAVE");
        } catch (IOException ioe) {
            System.out.println("Trouble reading from the file: " + ioe.getMessage());
        }
    }

}
