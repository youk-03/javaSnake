package implementation;

import lib.Position;
import lib.SlitherScene;

public class ArrowSnake extends MyControllableSnake<Direction>{
    public ArrowSnake(Position pos) {
        super(pos);
    }

    @Override
    public void choseDirection(SlitherScene scene) {
        Position pos= this.getPos();
        Direction lastInput= lastInput();
        if(lastInput == null){
            setCurrentDirection(null);
            return;
        }
        switch (lastInput){
                case UP: setCurrentDirection(new MyPosition(pos.getX(), pos.getY()-1)); break;
                case DOWN: setCurrentDirection(new MyPosition(pos.getX(),pos.getY()+1));break;
                case LEFT: setCurrentDirection(new MyPosition(pos.getX()-1,pos.getY()));break;
                case RIGHT:setCurrentDirection(new MyPosition(pos.getX()+1,pos.getY()));break;
                default: setCurrentDirection(new MyPosition(pos.getX(), pos.getY())); break;
        }
    }
}
