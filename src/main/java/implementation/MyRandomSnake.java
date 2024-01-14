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
        Position dir= MyPosition.getRandPos(SlitherScene.windowWidth, SlitherScene.windowHeight,0,0);
        while(!isValidMove(dir) || willDie(dir)){
            dir= MyPosition.getRandPos(SlitherScene.windowWidth, SlitherScene.windowHeight,0,0);
        }
        setCurrentDirection(dir);
    }
}
