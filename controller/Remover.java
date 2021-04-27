package Controller;

import Model.Clickable;
import View.MainFrame;

public class Remover extends Instrument implements Clickable {

    public MainFrame mn = null;

    public Remover(MainFrame mn) {
        this.mn = mn;
        int i;
        for ( i=0; i < mn.quaue.queue.size(); i++){
            if (mn.quaue.queue.get(i) == mn.quaue.activObject){
                mn.quaue.queue.remove(i);
                break;
            }
        }
        mn.quaue.list.remove(i);
        mn.quaue.activObject = null;
        mn.canvas.repaint();
    }

    @Override
    public void setFirstCordinate(int x, int y) {
        super.setFirstCordinate(x, y);
    }

    @Override
    public void setNextCordinate(int x, int y) {
        super.setNextCordinate(x, y);
    }

    @Override
    public void setLastCordinate(int x, int y) {
        super.setLastCordinate(x, y);
    }
}
