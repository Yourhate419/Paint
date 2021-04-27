package Controller;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import Model.Shape;

public class Queue implements Serializable {
    ArrayList<Shape> queue = new ArrayList<Shape>();
    Shape activObject = null;

    public Queue() {
        ActObj();
    }

    public List list = new List();
    public  void add (Shape fig) {
        queue.add(fig);
        activObject = queue.get(queue.size()-1);
        list.add(fig.getClass().getName().toString());
        list.select(queue.size()-1);
        //list.setSize(130, 300);
        //list.setBounds(750, 100, 130, 80);
        //
        ActObj();

    }

    public void ActObj(){

        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ewt) {
                if (ewt.getStateChange() == ItemEvent.SELECTED) {
                    int i = (int) ewt.getItem();
                    System.out.println(i);
                    activObject = queue.get(i);
                }
            }
        });
    }

    public Shape getComponenetObject(){
        if (activObject == null){
            System.out.println("Попытка обратиться к пустому активному объекту в очереди");
        }
        return activObject;
    }
    public void paintQuaue(Graphics g){

        int n = queue.size();
        for(int i=0; i < n; i++){
            Shape sh = queue.get(i);
            sh.paint(g);
        }
        if (activObject != null){
            //activObject.paint(g);
            activObject.drawBorder(g);
            //activObject.drawCenter(g);
        }


    }

    public void clear() {
        queue.clear();
        activObject = null;
    }
}
