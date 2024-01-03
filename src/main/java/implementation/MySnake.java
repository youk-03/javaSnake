package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lib.Grid;
import lib.Position;
import lib.Screen;
import lib.Snake;

import java.security.InvalidParameterException;

public class MySnake implements Snake {
    private Position<Double> position;
    private final Double radius= 5.0;
    private boolean isHead;
    private MySnake next;
    private MySnake previous;
    private static MySnake last;
    private Color headColor= Color.DARKGREEN, bodyColor= Color.FORESTGREEN;

    private Circle segment;
    private Position<Double> currentDirection;

    final double velocity= 1;

    /**Create a head a snake*/
    public MySnake(Position<Double> pos){
        currentDirection = new MyPosition(0,0);
        this.next= null;
        this.previous= null;
        this.position= pos;
        this.isHead = true;
        segment = null;
        if(isHead){
            last = this;
        }
    }

    /**Create a snake cell*/
    public MySnake(MySnake previous,MySnake next,Position<Double> pos){
        this.previous= previous;
        this.next= next;
        this.position= pos;
        this.isHead = false;
        segment = null;
        if(isHead){
            last = this;
        }
    }

    public Circle getSegment() {
        return segment;
    }

    @Override
    public Snake last() {
        return last;
    }

    @Override
    public void display(Screen screen) {
        segment = new Circle();
        segment.setCenterX(position.getX());
        segment.setCenterY(position.getY());
        segment.setRadius(radius);
        if (isHead()){
            segment.setFill(headColor);
        } else {
            segment.setFill(bodyColor);
        }
        screen.add(segment);
    }

    @Override
    public Position<Double> getPos() {
        Position<Double> pos= new MyPosition(position.getX(),position.getY());
        return pos;
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

        if(segment.next != null) {

            while (segment.next() != null) {
                segment = (MySnake) segment.next();
            }
            Position<Double> posSegm = segment.position;
            Position<Double> posSegmPrev = segment.previous.position;
            Position<Double> posNew = new MyPosition(
                    posSegm.getX() + (posSegm.getX() - posSegmPrev.getX()),
                    posSegm.getY() + (posSegm.getY() - posSegmPrev.getY()));
            segment.next = new MySnake(segment, null, posNew);
        }
        else {
            if(segment.isHead) {
                Position<Double> pos = this.position;
                Position<Double> posNew = new MyPosition(pos.getX() - (segment.radius * 2), pos.getY());
                segment.next = new MySnake(segment, null, posNew);
            }
        }
        last = segment.next;

        }


    @Override
    public void removeFrom() {
        previous.next=null;
    }

    @Override
    public double getX() {
        return position.getX();
    }

    @Override
    public double getY() {
        return position.getY();
    }

    @Override
    public void setPosition(double x, double y) {
        position.setXY(x,y);
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public Position getDirection() {
        if(!isHead()) throw new InvalidParameterException();
        return new MyPosition(currentDirection.getX(),currentDirection.getY());
    }

    @Override
    public void choseDirection(Grid grid) {
        throw new UnsupportedOperationException();
    }

    public void setCurrentDirection(Position pos){
        currentDirection = new MyPosition(pos.getX(),pos.getY());
    }

    public void moveCircle(){
        this.segment.setCenterX(this.position.getX());
        this.segment.setCenterY(this.position.getY());
    }

    public void setColor(Color head, Color body){
        headColor= head;
        bodyColor= body;
    }
}

//TODO faire un main pour le 1.
