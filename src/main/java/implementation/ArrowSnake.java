package implementation;

import lib.Grid;
import lib.Position;

public class ArrowSnake extends MySnake{
    Direction lastInput;

    public ArrowSnake(Position<Double> pos) {
        super(pos);
        this.lastInput= null;
    }

    public void setLastInput(Direction lastInput) {
        this.lastInput = lastInput;
    }

    @Override
    public void choseDirection(Grid grid) {
        if(lastInput==null) setCurrentDirection(new MyPosition(0,0));
        else {
            switch (lastInput){
                case UP: setCurrentDirection(new MyPosition(0,-1)); break;
                case DOWN: setCurrentDirection(new MyPosition(0,1));break;
                case LEFT: setCurrentDirection(new MyPosition(-1,0));break;
                case RIGHT:setCurrentDirection(new MyPosition(1,0));break;
            }
        }
    }
}
