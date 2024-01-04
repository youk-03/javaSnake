package implementation;

import lib.Grid;
import lib.Position;

public class ArrowSnake extends MyControllableSnake<Direction>{
    Direction lastInput;

    public ArrowSnake(Position<Double> pos) {
        super(pos);
    }

    @Override
    public void choseDirection(Grid grid) {
        Position pos= this.getPos();
        switch (lastInput()){
                case UP: setCurrentDirection(new MyPosition(pos.getX(), pos.getY()-1)); break;
                case DOWN: setCurrentDirection(new MyPosition(pos.getX(),pos.getY()+1));break;
                case LEFT: setCurrentDirection(new MyPosition(pos.getX()-1,pos.getY()));break;
                case RIGHT:setCurrentDirection(new MyPosition(pos.getX()+1,pos.getY()));break;
                default: setCurrentDirection(new MyPosition(pos.getX(), pos.getY())); break;
        }
    }
}
