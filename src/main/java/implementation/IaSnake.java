package implementation;

import javafx.scene.paint.Color;
import lib.Fruit;
import lib.Position;
import lib.SlitherScene;
import lib.Utils;

import java.util.List;

public class IaSnake extends MySnake{
    public IaSnake(Position pos) {
        super(pos,Color.DARKRED,Color.CRIMSON);
    }

    @Override
    public void choseDirection(SlitherScene scene){
        List<Fruit> fruits= scene.getFruitList();
        if(fruits == null || fruits.get(0) == null){
            setCurrentDirection(null);
            return;
        }

        Position pos= this.getPos();
        Position minPos= null;
        double min= -1;

        for(Fruit f:fruits){
            if(f.isTakable()){
                Position fpos= f.getPos();
                if(!willDie(fpos)){
                    if(minPos == null){
                        minPos= fpos;
                        min= Utils.distance(pos,fpos);
                    } else {
                        double fdist= Utils.distance(pos,fpos);
                        if(fdist<min){
                            minPos= fpos;
                            min= fdist;
                        }
                    }
                }
            }
        }

        if(minPos != null) this.setCurrentDirection(minPos);
    }
}
