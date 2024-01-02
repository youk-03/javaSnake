package lib;

public interface Grid {
    abstract GraphicalObject get(Position pos);
    abstract void add(GraphicalObject obj, Position pos);
    abstract void empty();
}
