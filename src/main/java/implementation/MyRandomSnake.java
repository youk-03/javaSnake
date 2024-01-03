package implementation;

import javafx.scene.paint.Color;
import lib.Grid;
import lib.Position;

import java.util.Random;

public class MyRandomSnake extends MySnake {


    public MyRandomSnake(Position<Double> pos) {
        super(pos);
        setColor(Color.DARKRED,Color.CRIMSON);
    }

    @Override
    public void choseDirection(Grid grid){
        Random rand= new Random();
        Position pos= this.getPos();
        setCurrentDirection(new MyPosition(rand.nextInt(),rand.nextInt()));
    }
}
