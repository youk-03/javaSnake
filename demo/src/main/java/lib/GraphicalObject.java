package lib;

public interface GraphicalObject {
    abstract void display(Screen screen);
    abstract Position getPos();
    abstract Double getRadius();
}
