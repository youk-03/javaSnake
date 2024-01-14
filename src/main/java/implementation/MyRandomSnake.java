package implementation;

import javafx.scene.paint.Color;
import lib.Position;
import lib.SlitherScene;

public class MyRandomSnake extends MySnake {


    public MyRandomSnake(Position pos) {
        super(pos,Color.DARKRED,Color.CRIMSON);
    }

    @Override
    public void choseDirection(SlitherScene scene){
        Position dir= MyPosition.getRandPos();
        while(!isValidMove(dir) || willDie(dir)){
            dir= MyPosition.getRandPos();
        }
        setCurrentDirection(dir);
    }
}
