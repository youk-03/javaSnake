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
        if (lastDirection == null) setCurrentDirection(new MyPosition(0, 0));
        else {
            Position mousePos= adapter.posOfMouse();
            Position pos= this.getPos();
            new MyPosition(
                    mousePos.getX() - pos.getX(),
                    mousePos.getY() - pos.getY());
        }
    }
}
