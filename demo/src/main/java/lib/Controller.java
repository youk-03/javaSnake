package lib;

public interface Controller {

    abstract void init(SlitherAdapter adapter);

    /** check if a move is required and call the needed function*/
    abstract void move();
}
