package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    private MySnake last;

    private Circle segment;
   private Direction currentDirection;

    final double velocity= radius/2;


    public MySnake(Position<Double> pos, boolean isHead, MySnake last){
        currentDirection = null;
        this.next= null;
        this.previous= null;
        this.position= pos;
        this.isHead = isHead;
        segment = null;
        if(isHead){
            last = this;
        }
        else{
            this.last = last;
        }
    }

    public MySnake(MySnake previous,MySnake next,Position<Double> pos, boolean isHead, MySnake last){
        currentDirection = null;
        this.previous= previous;
        this.next= next;
        this.position= pos;
        this.isHead = isHead;
        segment = null;
        if(isHead){
            last = this;
        }
        else{
            this.last = last;
        }
    }

    @Override
    public void display(Screen screen) {
        segment = new Circle();
        segment.setCenterX(position.getX());
        segment.setCenterY(position.getY());
        segment.setRadius(radius);
        if (isHead()){
            segment.setFill(Color.GREEN);
        } else {
            segment.setFill(Color.FORESTGREEN);
        }
        screen.add(segment);
    }

    @Override
    public Position<Double> getPos() {
        Position<Double> pos= new MyPosition(position.getX(),position.getY());
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
    public Snake last() {
        return last;
    }

    public void setLast(MySnake last) {
        this.last = last;
    }

    @Override
    public void add() { //ici problème si on est la head et que prev est null
        MySnake segment= this;
        while(segment.next() != null){
            segment= (MySnake) segment.next();
        }
        Position<Double> posSegm= segment.position;
        Position<Double> posSegmPrev= segment.previous.position;
        Position<Double> posNew= new MyPosition(
                posSegm.getX()+(posSegm.getX()-posSegmPrev.getX()),
                posSegm.getY()+(posSegm.getY()-posSegmPrev.getY()));
        segment.next= new MySnake(segment,null,posNew,false,segment);
    }

    @Override
    public void removeFrom() {
        previous.next=null;
    }

    public void setCurrentDirection(Direction dir){
        currentDirection = dir;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }


    public void moveCircle(){
        this.segment.setCenterX(this.position.getX());
        this.segment.setCenterY(this.position.getY());
    }


    public void move(){
        MySnake tmp = last;
        if(!isHead){
            throw new InvalidParameterException();
        }
        if(currentDirection == null){
           System.out.println("currentDirection est null");
        }
        switch (currentDirection){ //border of the screen test
            case UP: if(this.position.getY()-velocity<0) return; break;
            case DOWN: if(this.position.getY()+velocity>lib.SlitherScene.windowHeight) return; break;
            case LEFT: if(this.position.getX()-velocity<0) return; break;
            case RIGHT:if(this.position.getX()+velocity>lib.SlitherScene.windowWidth) return; break;
        }
        while(tmp != this){ //moving the segment
            tmp.previous.getPos().setY(tmp.getPos().getY());
            tmp.moveCircle();
            tmp = tmp.previous;
        }
        switch (currentDirection){ //moving the head
            case UP: tmp.position.setY(this.position.getY()-velocity); break;
            case DOWN: tmp.position.setY(this.position.getY()+velocity); break;
            case LEFT: tmp.position.setX(this.position.getX()-velocity);break;
            case RIGHT:tmp.position.setX(this.position.getX()+velocity); break;
        }
        tmp.moveCircle();


    }
}

//TODO interface Scene
//TODO faire un main pour le 1.
