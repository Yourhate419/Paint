package Model;

import java.io.Serializable;

public class Point<T> implements Cloneable, Serializable {
    T x;
    T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T GetX() {
        return x;
    }

    public T GetY() {
        return y;
    }

    public Object Clone() {
        return new Point(x, y);
    }
}