package lib;

public interface ControllableSnake<T> extends Snake{
    abstract void setLastInput(T lastInput);

    abstract T lastInput();
}
