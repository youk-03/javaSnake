package lib;

import implementation.MyPosition;
import java.util.List;

public interface Fruit extends GraphicalObject{
    abstract boolean isVisible();

    abstract void setInvisible();

    abstract void setVisible();

    static void displayAFruit(List<Fruit> listFruit, List<Snake> snakes){
        boolean test = false;
        for (Fruit f: listFruit) {
            if(!f.isVisible()){
                Position pos = f.getPos();

                //TESTER D ABORD SI C'EST PAS SUR SNAKE DEJA EXISTANT OU UN FRUIT DONC UNE POSITION INVALIDE
                while(!test){
                    pos = MyPosition.getRandPos();
                    if(!pos.isIn(listFruit)){
                        for (Snake s: snakes ) {
                            if(!pos.isIn(s)) { test = true;}
                            else {
                                test = false;
                                break;
                            }
                        }
                    }
                }

                f.setPosition(pos.getX(), pos.getY());
                f.setFruitPos();
                f.setVisible();
                return;
            }
        }
    }

    abstract void setFruitPos();

}
