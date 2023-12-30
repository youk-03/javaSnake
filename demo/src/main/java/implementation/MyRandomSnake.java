package implementation;

import lib.Grid;
import lib.Position;

import java.security.InvalidParameterException;
import java.util.Random;

public class MyRandomSnake extends MySnake {


    public MyRandomSnake(Position<Double> pos) {
        super(pos);
    }

    @Override
    public void choseDirection(Grid grid){
        Random rand= new Random();
        setCurrentDirection((rand.nextDouble()*360) % 360);
    }
}
