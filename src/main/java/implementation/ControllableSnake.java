package implementation;

import lib.Position;

public class ControllableSnake<T> extends MySnake{
    T lastInput;

    public ControllableSnake(Position<Double> pos) {
        super(pos);
        this.lastInput= null;
    }

    public void setLastInput(T lastInput) {
        this.lastInput = lastInput;
    }

    public T lastInput(){return  lastInput;}
}
