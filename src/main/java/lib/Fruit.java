package lib;

import implementation.MyPosition;
import java.util.List;

public interface Fruit extends GraphicalObject{

    /**@return true if the fruit is visible in game*/
    abstract boolean isVisible();

    /** Make the fruit non-visible in game*/
    abstract void setInvisible();

    /** Make the fruit visible in game*/
    abstract void setVisible();

    /** Display a random fruit and make it visible in game*/
    static void displayAFruit(List<Fruit> listFruit, List<Snake> snakes, boolean onScreen){
        boolean test = false;
        for (Fruit f: listFruit) {
            if(!f.isVisible()){
                Position pos = f.getPos();

                //TESTER D ABORD SI C'EST PAS SUR SNAKE DEJA EXISTANT OU UN FRUIT DONC UNE POSITION INVALIDE
                while(!test){
                    if(onScreen) {
                        pos = MyPosition.getRandPos();
                    }
                    else{
                        pos = MyPosition.getRandPosInvisible();
                    }
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
                f.setIsTakable(true);

                return;
            }
        }
    }

    abstract void setFruitPos();

    /**@return true if the fruit is currently eatable*/
    abstract boolean isTakable();

    /**Change the interactivity of a fruit
     * @param bool true to set the fruit eatable and false to set the fruit non-eatable.*/
    abstract void setIsTakable(boolean bool);

    static void setPosForAllFruit(double x, double y, List<Fruit> fruitList){
        double newX;
        double newY;
        for (Fruit f: fruitList) {
            if(f.isTakable() && f.isVisible()){
                 newX = f.getPos().getX() - x;
                 newY = f.getPos().getY() - y;

                if(newX > SlitherScene.windowWidth*2) {
                    newX = -SlitherScene.windowWidth;
                }
                if(newX < -SlitherScene.windowWidth){
                    newX = SlitherScene.windowWidth*2;
                }
                if(newY > SlitherScene.windowHeight*2){
                    newY = -SlitherScene.windowHeight;
                }
                if(newY < -SlitherScene.windowHeight) {
                    newY = SlitherScene.windowHeight*2;
                }

                f.setPosition(newX, newY);
                f.setFruitPos();
            }

        }
    }

}
