package implementation;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import lib.Position;
import lib.SlitherScene;

import java.util.Random;

public class MyRandomSnake extends MySnake {


    public MyRandomSnake(Position pos) {
        super(pos);
        setColor(Color.DARKRED,Color.CRIMSON);
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
