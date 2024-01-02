package implementation;

import lib.Grid;
import lib.Position;

public class ControllableSnake extends MySnake{
    Direction lastInput;

    public ControllableSnake(Position<Double> pos) {
        super(pos);
        this.lastInput= null;
    }

    public void setLastInput(Direction lastInput) {
        this.lastInput = lastInput;
    }

    @Override
    public void choseDirection(Grid grid) {
        if(lastInput==null) setCurrentDirection(0);
        else switch (lastInput){
            case UP: setCurrentDirection(270); break;
            case DOWN: setCurrentDirection(90);break;
            case LEFT: setCurrentDirection(180);break;
            case RIGHT:setCurrentDirection(0);break;
        }
    }
}
