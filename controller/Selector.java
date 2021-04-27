package Controller;

import Model.Clickable;
import View.MainFrame;

public class Selector extends Instrument implements Clickable {

    MainFrame mn = null;

    public Selector(MainFrame mn) {this.mn = mn;}


    @Override
    public void setFirstCordinate(int x, int y) {
        for (int i = 0; i < mn.quaue.queue.size(); i++) {
            if (mn.quaue.queue.get(i).into(x, y)) {
                mn.quaue.activObject = mn.quaue.queue.get(i);

                mn.quaue.list.select(i);
            }
        }
        mn.quaue.paintQuaue(mn.canvas.g1);
        mn.activeInstrument = new EmptyInstrument(mn);

    }

    @Override
    public void setLastCordinate(int x, int y){

    }

    @Override
    public void setNextCordinate(int x, int y){

    }
}
