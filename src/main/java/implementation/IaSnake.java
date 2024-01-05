package implementation;

import javafx.scene.paint.Color;
import lib.Fruit;
import lib.Position;
import lib.SlitherScene;
import lib.Utils;

import java.util.List;

public class IaSnake extends MySnake{
    public IaSnake(Position<Double> pos) {
        super(pos);
        setColor(Color.DARKRED,Color.CRIMSON);
    }

    @Override
    public void choseDirection(SlitherScene scene){
        List<Fruit> fruits= scene.getFruitList();
        if(fruits == null || fruits.get(0) == null){
            setCurrentDirection(null);
            return;
        }

        Position pos= this.getPos();
        Position minPos= fruits.get(0).getPos();
        double min= Utils.distance(pos,minPos);

        for(Fruit f:fruits){
            Position fpos= f.getPos();
            double fdist= Utils.distance(pos,fpos);
            if(fdist<min){
                minPos= fpos;
                min= fdist;
            }
        }

        setCurrentDirection(minPos);
    }
}
