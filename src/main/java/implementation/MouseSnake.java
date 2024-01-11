package implementation;

import lib.Position;
import lib.SlitherScene;

public class MouseSnake extends MyControllableSnake<Position>{
    public MouseSnake(Position pos) {
        super(pos);
    }

    @Override
    public void choseDirection(SlitherScene scene) {
        Position mousePos= lastInput();
        if (mousePos == null){
            setCurrentDirection(null);
            return;
        }
        Position pos= this.getPos();
        double dx= pos.getX()-mousePos.getX();
        double dy= pos.getY()-mousePos.getY();
        double rad= getRadius();
        if((dy<rad && dy>-rad) || (dx<rad && dx>-rad)) setCurrentDirection(null);
        else {
            setCurrentDirection(new MyPosition(mousePos.getX(), mousePos.getY()));
        }
    }
}
