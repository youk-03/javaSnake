package lib;

import java.util.ArrayList;

public interface Position<T extends Double> {
    abstract T getX();
    abstract T getY();
    abstract void setX(T x);
    abstract void setY(T y);
    abstract void setXY(T x,T y);

    abstract boolean isValid(ArrayList<GraphicalObject> e);

    abstract boolean isValid(Snake head);
}
