package implementation;

import lib.Grid;
import lib.Position;
import lib.SlitherAdapter;
import lib.SlitherScene;

public class MouseSnake extends ControllableSnake<Position>{
    public MouseSnake(Position<Double> pos) {
        super(pos);
    }

    @Override
    public void choseDirection(Grid grid) {
        Position mousePos= lastInput();
        if (mousePos == null){
            Position pos= this.getPos();
            setCurrentDirection(new MyPosition(pos.getX(), pos.getY()));
        }
        else {
            setCurrentDirection(new MyPosition(mousePos.getX(), mousePos.getY()));
        }
    }
}
