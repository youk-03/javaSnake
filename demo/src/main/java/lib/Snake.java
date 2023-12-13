package lib;

public interface Snake extends GraphicalObject{
    abstract boolean isHead();
    abstract Snake next();
    abstract Snake pred();
    abstract void add();
    abstract void remove();
}
