package implementation;

import lib.Grid;
import lib.Position;

import java.util.Random;

public class MyRandomSnake extends MySnake {


    public MyRandomSnake(Position<Double> pos) {
        super(pos);
    }

    @Override
    public void choseDirection(Grid grid){
        Random rand= new Random();
        Position pos= this.getPos();
        setCurrentDirection(new MyPosition(rand.nextInt()- pos.getX(),rand.nextInt() - pos.getY()));
    }
}
