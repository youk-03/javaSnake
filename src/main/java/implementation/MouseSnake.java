package implementation;

import lib.Grid;
import lib.Position;
import lib.SlitherAdapter;

public class MouseSnake extends MySnake{
    Position lastDirection;
    SlitherAdapter adapter;

    public MouseSnake(Position<Double> pos, SlitherAdapter adapter) {
        super(pos);
        lastDirection= null;
        adapter= adapter;
    }

    @Override
    public void choseDirection(Grid grid) {
        Position pos= this.getPos();
        if (lastDirection == null) setCurrentDirection(new MyPosition(pos.getX(), pos.getY()));
        else {
            Position mousePos= adapter.posOfMouse();
            new MyPosition(
                    mousePos.getX() - pos.getX(),
                    mousePos.getY() - pos.getY());
        }
    }
}
