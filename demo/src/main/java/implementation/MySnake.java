package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lib.Position;
import lib.Screen;
import lib.Snake;

public class MySnake implements Snake {
    private Position position;
    private Double radius= 5.0;
    private boolean isHead;
    private MySnake next;
    private MySnake previous;

    public MySnake(){
        this.next= null;
        this.previous= null;
    }

    public MySnake(MySnake previous,MySnake next){
        this.previous= previous;
        this.next= next;
    }

    @Override
    public void display(Screen screen) {
        Circle display= new Circle();
        display.setCenterX(position.getX());
        display.setCenterY(position.getY());
        display.setRadius(radius);
        if (isHead()){
            display.setFill(Color.GREEN);
        } else {
            display.setFill(Color.FORESTGREEN);
        }
        screen.add(display);
    }

    @Override
    public Position getPos() {
        Position pos= new MyPosition(position.getX(),position.getY());
        return position;
    }

    @Override
    public Double getRadius() {
        return radius;
    }

    @Override
    public boolean isHead() {
        return isHead;
    }

    @Override
    public Snake next() {
        return next;
    }

    @Override
    public Snake prev() {
        return previous;
    }

    @Override
    public void add() {
        MySnake segment= this;
        while(segment.next() != null){
            segment= (MySnake) segment.next();
        }
        segment.next= new MySnake(segment,null);
    }

    @Override
    public void removeFrom() {
        previous.next=null;
    }
}
