package lib;

public interface ControllableSnake<T> extends Snake{
    /** Give a new input to the snake
     * @param lastInput the input you want to give*/
    abstract void setLastInput(T lastInput);

    /** Get the last input given to the snake
     * @return the last input given to the snake*/
    abstract T lastInput();
}
