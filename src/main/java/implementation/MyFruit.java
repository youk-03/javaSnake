package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lib.*;
import java.util.ArrayList;
import java.util.List;


public class MyFruit implements Fruit {

    private Position pos;
    private double radius = 5.0;

    private Circle fruit;

    private static int visibleFruit = 10;
    private static int invisibleFruit = 100;
    private boolean isTakable;

    private static final Color[] possibleColor = {Color.BLUE, Color.BLUEVIOLET, Color.CHOCOLATE, Color.DEEPPINK};

    private static List<Fruit> listFruit = new ArrayList<>();
    public MyFruit (double x, double y) {
        pos = new MyPosition(x,y);
        fruit = new Circle();
        fruit.setCenterY(y);
        fruit.setCenterX(x);
        fruit.setRadius(radius);
        fruit.setFill(possibleColor[(int)(Math.random()*possibleColor.length)]);
        fruit.setVisible(false);
        isTakable = false;
    }

   public static void init(Screen screen, List<Snake> snakes){ //to call only one time at first, init with 50 fruit
        MyFruit f = null;
        for(int i=0; i<150; i++){
            f = new MyFruit(-1,-1);
            listFruit.add(f);
            f.display(screen);
        }
        //10 fruit constamment visible sur l'écran
       //100 partout autour pas sur l'écran !
       //50 en rab ?

        //we start by making 10 fruits visible on the screen
       for(int i=0; i<visibleFruit; i++){
            Fruit.displayAFruit(listFruit, snakes, true,false,null);
        }
        if(MainScene.scrolling) {
            for (int i = 0; i < invisibleFruit; i++) {
                Fruit.displayAFruit(listFruit, snakes, false,false,null);
            }
        }
   }

    @Override
    public void display(Screen screen) {
        screen.add(this.fruit);
    }

    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setPosition(double x, double y) {
        this.pos.setY(y);
        this.pos.setX(x);
    }


    @Override
    public boolean isVisible(){
        return fruit.isVisible();
    }

    @Override
    public void setInvisible(){
        fruit.setVisible(false);
        this.setPosition(-1,-1);
        this.setFruitPos();
        this.setIsTakable(false);
    }


    @Override
    public void setVisible(){
        fruit.setVisible(true);
    }


    @Override
    public void setFruitPos() {
        fruit.setCenterX(this.getPos().getX());
        fruit.setCenterY(this.getPos().getY());
    }

    @Override
    public void setIsTakable(boolean bool) {
        this.isTakable = bool;
    }

    public boolean isTakable() {
        return isTakable;
    }

    public static List<Fruit> getListFruit() {
        return listFruit;
    }



}
