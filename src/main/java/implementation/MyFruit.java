package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lib.Fruit;
import lib.Position;
import lib.Screen;
import lib.Snake;

import java.util.ArrayList;

public class MyFruit implements Fruit {

    private Position<Double> pos;
    private double radius = 5.0;

    private Circle fruit;

    private static int visibleFruit = 10;

    private static final Color[] possibleColor = {Color.BLUE, Color.BLUEVIOLET, Color.CRIMSON, Color.CHOCOLATE, Color.DEEPPINK};

    private static ArrayList<Fruit> listFruit = new ArrayList<>();
    public MyFruit (double x, double y) {
        pos = new MyPosition(x,y);
        fruit = new Circle();
        fruit.setCenterY(y);
        fruit.setCenterX(x);
        fruit.setRadius(radius);
        fruit.setFill(possibleColor[(int)(Math.random()*5)]);
        fruit.setVisible(false);
    }

   public static void init(Screen screen, ArrayList<Snake> snakes){ //to call only one time at first, init with 50 fruit
        MyFruit f = null;
        for(int i=0; i<50; i++){
            f = new MyFruit(-1,-1);
            listFruit.add(f);
            f.display(screen);
        }

        //we start by making 10 fruits visible on the screen
       for(int i=0; i<visibleFruit; i++){
            Fruit.displayAFruit(listFruit, snakes);
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
    public Double getRadius() {
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
        fruit.setVisible(false); //CHANGER SES VAL DECREMENT ISVISIBLE
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


}
