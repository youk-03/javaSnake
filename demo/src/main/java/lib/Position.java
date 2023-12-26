package lib;

public interface Position<T extends Double> {
    abstract T getX();
    abstract T getY();
    abstract void setX(T x);
    abstract void setY(T y);
    abstract void setXY(T x,T y);
}
