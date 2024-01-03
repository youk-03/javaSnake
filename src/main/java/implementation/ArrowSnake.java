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
        Position pos= this.getPos();
        if (lastInput == null) setCurrentDirection(new MyPosition(pos.getX(), pos.getY()));
        else {
            switch (lastInput){
                case UP: setCurrentDirection(new MyPosition(pos.getX(), pos.getY()-1)); break;
                case DOWN: setCurrentDirection(new MyPosition(pos.getX(),pos.getY()+1));break;
                case LEFT: setCurrentDirection(new MyPosition(pos.getX()-1,pos.getY()));break;
                case RIGHT:setCurrentDirection(new MyPosition(pos.getX()+1,pos.getY()));break;
            }
        }
    }
}
