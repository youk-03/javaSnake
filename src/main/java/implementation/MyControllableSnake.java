package implementation;

import lib.ControllableSnake;
import lib.Position;

public class MyControllableSnake<T> extends MySnake implements ControllableSnake {
   private T lastInput;

    public MyControllableSnake(Position pos) {
        super(pos);
        this.lastInput= null;
    }

    @Override
    public void setLastInput(Object lastInput) {
        this.lastInput = (T) lastInput;
    }

    @Override
    public T lastInput(){return  lastInput;}
}
