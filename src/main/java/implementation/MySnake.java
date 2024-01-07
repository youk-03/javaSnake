package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lib.*;
import java.security.InvalidParameterException;
import java.util.List;

public class MySnake implements Snake {
    private Position<Double> position;
    private final Double radius= 5.0;
    private boolean isHead;
    private MySnake next;
    private MySnake previous;
    private Color headColor, bodyColor;

    private Circle segment;
    private Position<Double> currentDirection;

    final double velocity= radius*2;

    /**Create a head a snake*/
    public MySnake(Position<Double> pos){
        currentDirection = new MyPosition(0,0);
        this.next= null;
        this.previous= null;
        this.position= pos;
        this.isHead = true;
        segment = null;
        this.headColor= Color.DARKGREEN;
        this.bodyColor= Color.FORESTGREEN;
    }

    /**Create a snake cell*/
    private MySnake(MySnake previous,MySnake next,Position<Double> pos){
        this.previous= previous;
        this.previous.next= this;
        this.next= next;
        this.position= pos;
        this.isHead = false;
        segment = null;
        this.bodyColor= previous.bodyColor;
    }

    public Circle getSegment() {
        return segment;
    }

    @Override
    public Snake last() {
        MySnake segment= this;
        while (segment.next != null){
            segment= segment.next;
        }
        return segment;
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
        if(this.next != null) {
            MySnake last= (MySnake) this.last();
            Position posSegm = last.getPos();
            Position posSegmPrev = last.prev().getPos();
            Position<Double> posNew= new MyPosition(
                    posSegm.getX() + (posSegm.getX() - posSegmPrev.getX()),
                    posSegm.getY() + (posSegm.getY() - posSegmPrev.getY()));
            new MySnake(last, null, posNew);
        } else {
            double[] vpos= Utils.velocityVector(position.getX(),position.getY(),currentDirection.getX(), currentDirection.getY(), velocity);
            Position<Double> posNew = new MyPosition(
                    position.getX() - vpos[0],
                    position.getY() - vpos[1]);
            new MySnake(this, null, posNew);
        }
        /*Position posLast= last.getPos();
        double[] vpos= Utils.velocityVector(posLast.getX(),posLast.getY(),currentDirection.getX(), currentDirection.getY(), velocity);
        Position<Double> posNew = new MyPosition(
                posLast.getX() - vpos[0],
                posLast.getY() - vpos[1]);
        last= new MySnake(last, null, posNew);*/
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
        if(currentDirection==null) return null;
        return new MyPosition(currentDirection.getX(),currentDirection.getY());
    }

    @Override
    public void choseDirection(SlitherScene scene) {
        throw new UnsupportedOperationException();
    }

    public void setCurrentDirection(Position pos){
        if(pos == null) currentDirection= null;
        else currentDirection = new MyPosition(pos.getX(),pos.getY());
    }

    public void moveCircle(){
        this.segment.setCenterX(this.position.getX());
        this.segment.setCenterY(this.position.getY());
    }

    public void setColor(Color head, Color body){
        headColor= head;
        bodyColor= body;
    }

    public boolean isTouching(GraphicalObject obj){
        //comparer avec this
        double xObj = obj.getPos().getX();
        double yObj = obj.getPos().getY();

        if(!this.isHead){
            throw new IllegalCallerException();
        }

        double rad= radius;
        if(obj instanceof Snake) rad--; //to make sure
        if(yObj - (rad+obj.getRadius()) <= this.getY() && this.getY() <= yObj + (rad+obj.getRadius())){
            if(xObj + rad >= this.getX() && this.getX() >= xObj - rad) return true;
        }

        else if(xObj - (rad+obj.getRadius()) <= this.getX() && this.getX() <= xObj + (rad+obj.getRadius())){
            if(yObj + rad >= this.getY() && this.getY() >= yObj - rad) return true;
        }
        return false;
    }

    public boolean isDead(){
        //compare avec tout les segment du serpent , a appelé avant de bouger le serpent une fois que la nouvelle pos a été calculée
        if(!this.isHead){
            throw new IllegalCallerException();
        }
        Snake tmp = this.next;
        while(tmp != null){
            if(this.isTouching(tmp)) { return true; }
            tmp = tmp.next();
        }
        return false;
    }

    public boolean isTouchingSom (List<Fruit> list){ //faire disparaitre le fruit + changer ses val + head.add + head.last.display (:
        if(!this.isHead){
            throw new IllegalCallerException();
        }
        for (Fruit g: list) {
            if(this.isTouching(g)) {
                g.setInvisible();
                return true;
            }
        }
        return false;
    }
}

